package todolist.mytwistedidea.wordpress.com.todolist;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Nishant on 22-02-2017.
 */

public class NonScrollableLV extends ListView {
    public NonScrollableLV(Context context) {
        super(context);
    }
    public NonScrollableLV(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public NonScrollableLV(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxHeightSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, maxHeightSpec);
    }
}
