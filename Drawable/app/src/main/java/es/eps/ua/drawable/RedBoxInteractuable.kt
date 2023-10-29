package es.eps.ua.drawable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class RedBoxInteractuable : View {

    var rectPosX = 0F
    var rectPosY = 0F

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int ) : super (context, attrs, defStyle) {}



    override fun onDraw(canvas: Canvas) {
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 5F
        paint.color = Color.RED

        drawRect(paint, canvas)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event!= null)
            if(event.action == MotionEvent.ACTION_DOWN) {
                if (!checkClickOnRectangle(event.x, event.y) ){
                    return false
                }
                rectPosX = event.x
                rectPosY = event.y
                this.invalidate()
            }
            else if (event.action == MotionEvent.ACTION_MOVE) {
                rectPosX = event.x
                rectPosY = event.y
                this.invalidate()
            }
        return true

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

    private fun drawRect(paint: Paint, canvas: Canvas)
    {


        val rect = RectF(
                rectPosX - RECTANGLE_WIDTH,
                rectPosY - RECTANGLE_HEIGHT,
                rectPosX + RECTANGLE_WIDTH,
                rectPosY + RECTANGLE_HEIGHT )
        canvas.drawRect(rect,paint)
    }


    companion object {
        const val RECTANGLE_WIDTH = 50F
        const val RECTANGLE_HEIGHT = 50F
        const val DEFAULT_SIZE = 300
    }




}