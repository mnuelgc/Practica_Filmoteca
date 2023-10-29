package es.eps.ua.drawable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat

class RedBoxInteractuableWithGestures : View, GestureDetector.OnGestureListener {

    var rectPosX = 0F
    var rectPosY = 0F
    var mDetector: GestureDetectorCompat? = null
    var rectMoving = false
    var isRectRed = true

    var haveToDrawLine = false

    var lineStartX = 0F
    var lineStartY = 0F
    var lineEndX = 0F
    var lineEndY = 0F

    constructor(context: Context?) : super(context) {initialize(context!!)}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {initialize(context!!)}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int ) : super (context, attrs, defStyle) {initialize(context!!)}


    private fun initialize(contexto: Context){
        mDetector = GestureDetectorCompat(contexto,this)
    }

    override fun onDraw(canvas: Canvas) {
        canvas
        drawRect(canvas)

        if (haveToDrawLine)
            drawLine(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        mDetector?.onTouchEvent(event!!)
        return true
    }

    override fun onDown(p0: MotionEvent): Boolean {
            if (!checkClickOnRectangle(p0.x, p0.y) ){
                rectMoving = false
                return false
            }
            rectPosX = p0.x
            rectPosY = p0.y
            this.invalidate()
            rectMoving = true
        return true
    }


    override fun onShowPress(p0: MotionEvent) {
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        if (!checkClickOnRectangle(p0.x, p0.y) ){
            return false
        }
        isRectRed = !isRectRed
        invalidate()
        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        if (rectMoving)
        {
            haveToDrawLine = false
            rectPosX = p1.x
            rectPosY = p1.y
            this.invalidate()
            return true
        }
        return false
    }

    override fun onLongPress(p0: MotionEvent) {

    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {

        if(rectMoving)
        {
            haveToDrawLine = true
            rectPosX = p1.x
            rectPosY = p1.y

            lineStartX = p0!!.x
            lineStartY = p0.y
            lineEndX = p1.x
            lineEndY = p1.y
            invalidate()
            return true
        }
        return false
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var width: Int = DEFAULT_SIZE
        var height: Int = DEFAULT_SIZE
        when (widthMode) {
            MeasureSpec.EXACTLY -> width = widthSize
            MeasureSpec.AT_MOST -> if (width > widthSize) width = widthSize
        }
        when (heightMode) {
            MeasureSpec.EXACTLY -> height = heightSize
            MeasureSpec.AT_MOST -> if (height > heightSize) height = heightSize
        }
        rectPosX  = width.toFloat() / 2
        rectPosY  = height.toFloat() / 2

        setMeasuredDimension(width, height)
    }

    private fun checkClickOnRectangle(pointX: Float, pointY: Float) : Boolean
    {
        val rectangleLeftSide = rectPosX - RECTANGLE_WIDTH
        val rectangleRightSide = rectPosX + RECTANGLE_WIDTH

        val rectangleUpSide = rectPosY - RECTANGLE_HEIGHT
        val rectangleDownSide = rectPosY + RECTANGLE_HEIGHT


        if ((pointX in rectangleLeftSide..rectangleRightSide)
                && pointY in rectangleUpSide..rectangleDownSide )
        {
            return true
        }

        return false
    }

    private fun drawRect(canvas: Canvas)
    {
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 5F

        if(isRectRed) {
            paint.color = Color.RED
        }else {
            paint.color = Color.BLUE
        }

        val rect = RectF(
                rectPosX - RECTANGLE_WIDTH,
                rectPosY - RECTANGLE_HEIGHT,
                rectPosX + RECTANGLE_WIDTH,
                rectPosY + RECTANGLE_HEIGHT )

        canvas.drawRect(rect,paint)
    }

    private fun drawLine(canvas: Canvas)
    {
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 25F
        paint.color = Color.GREEN

        canvas.drawLine(lineStartX,lineStartY,lineEndX,lineEndY,paint)
    }


    companion object {
        const val RECTANGLE_WIDTH = 50F
        const val RECTANGLE_HEIGHT = 50F
        const val DEFAULT_SIZE = 300
    }



}