package online.daniilk.moneytracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class DiagramView extends View {
    private int income;
    private int expense;
    private Paint incomePaint = new Paint();
    private Paint expensePaint = new Paint();

    public DiagramView(Context context) {
        this(context, null);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // Canvas - форма(холст) под которым можно рисовать
        // Paint -  оформление (кисть)

        incomePaint.setColor(getResources().getColor(R.color.balance_income_color));
        expensePaint.setColor(getResources().getColor(R.color.balance_expense_color));

        if (isInEditMode()) { // отображение в превью(AndroidStudio)
            income = 19000;
            expense = 4500;
        }
    }


    public void update(int income, int expense) {
        this.income = income;
        this.expense = expense;
//        requestLayout(); // перерисовка onMeasure вымерение размера
        invalidate(); // только onDraw
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        // задание размера и его ограничение
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthValue = MeasureSpec.getSize(widthMeasureSpec);
//
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightValue = MeasureSpec.getSize(heightMeasureSpec);
//
//        setMeasuredDimension(100, 100);
//
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
////    event.getAction() == MotionEvent.ACTION_DOWN;
////        event.getX()
//
//        return super.onTouchEvent(event);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            drawPieDiagram(canvas);
        else
            drawRectDiagram(canvas);
        //        expensePaint = new Paint();
        // избегать создание в onDraw отрисовка в 16мс - 60fps, min 24fps
    }


    private void drawRectDiagram(Canvas canvas) {
        if (expense + income == 0)
            return;

        long max = Math.max(expense, income); // Кто выше доход или расход?
        long expensesHeight = canvas.getHeight() * expense / max; // процент на высоту
        long incomeHeight = canvas.getHeight() * income / max;

        int w = getWidth() / 4;

        // Нарисовать квадрат
        canvas.drawRect(w / 2, canvas.getHeight() - expensesHeight, w * 3 / 2, canvas.getHeight(), expensePaint);
        canvas.drawRect(5 * w / 2, canvas.getHeight() - incomeHeight, w * 7 / 2, canvas.getHeight(), incomePaint);
        //  углы: левый, верхний, правый, нижний
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawPieDiagram(Canvas canvas) {
        if (expense + income == 0)
            return;

        float expenseAngle = 360.f * expense / (expense + income); // Высчитываем углы
        float incomeAngle = 360.f * income / (expense + income);

        int space = 10; // px пространства между ими
        int size = Math.min(getWidth(), getHeight()) - space * 2;
        final int xMargin = (getWidth() - size) / 2, yMargin = (getHeight() - size) / 2;

        canvas.drawArc(xMargin - space, yMargin, getWidth() - xMargin - space, getHeight() - yMargin, 180 - expenseAngle / 2, expenseAngle, true, expensePaint);
        canvas.drawArc(xMargin + space, yMargin, getWidth() - xMargin + space, getHeight() - yMargin, 360 - incomeAngle / 2, incomeAngle, true, incomePaint);
// Кусок

    }

}
