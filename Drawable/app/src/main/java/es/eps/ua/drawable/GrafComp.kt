package es.eps.ua.drawable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.widget.SeekBar

class GrafComp : View {
    constructor(context: Context?) : super(context) {inicializar(null)}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {inicializar(attrs)}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int ) : super (context, attrs, defStyle) {inicializar(attrs)}

    private var percentage = 0

    private fun inicializar(attrs: AttributeSet?)
    {
        if(attrs == null) return
        val ta = context.obtainStyledAttributes(
            attrs, R.styleable.Grafica)
        this.percentage = ta.getInt(R.styleable.Grafica_percentage, 0)
    }

    override fun onDraw(canvas: Canvas) {
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 5F
        paint.color = Color.BLUE

        val paintRed = Paint()
        paintRed.style = Paint.Style.FILL
        paintRed.strokeWidth = 5F
        paintRed.color = Color.RED

        var r = DEFAULT_SIZE.toFloat()
        r =canvas.clipBounds.height().toFloat() /2

        val oval = RectF()

        val arcExtension : Float = -360F * (percentage.toFloat()/100F)

        oval.set(canvas.clipBounds.width().toFloat()/2 -r, canvas.clipBounds.height().toFloat()/2 -r,
                canvas.clipBounds.width().toFloat()/2 + r, canvas.clipBounds.height().toFloat()/2 + r)

        canvas!!.drawCircle(canvas.clipBounds.width().toFloat()/2 ,canvas.clipBounds.height().toFloat()/2 , r , paint)
        canvas!!.drawArc(oval,0F, arcExtension,true, paintRed)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var width: Int = Companion.DEFAULT_SIZE
        var height: Int = Companion.DEFAULT_SIZE
        when (widthMode) {
            MeasureSpec.EXACTLY -> width = widthSize
            MeasureSpec.AT_MOST -> if (width > widthSize) width = widthSize
        }
        when (heightMode) {
            MeasureSpec.EXACTLY -> height = heightSize
            MeasureSpec.AT_MOST -> if (height > heightSize) height = heightSize
        }
        setMeasuredDimension(width, height)
    }

    companion object {
        const val DEFAULT_SIZE = 100
    }
    public fun setPercentage(newPercentage: Int){
        this.percentage = newPercentage
        invalidate()
    }
}