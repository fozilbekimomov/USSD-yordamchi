package uz.fozilbekimomov.ussdyordamchi.core.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by <a href="mailto:fozilbekimomov@gmail.com">Fozilbek Imomov</a>
 *
 * @author Fozilbek Imomov
 * @version 1.0
 * @since 6/11/2020.
 */

class UniversalGridItemDecoration(private val spanCount: Int, private val spacing: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val position = parent.getChildAdapterPosition(view)
        val count = parent.childCount

        outRect.left = spacing / 2
        outRect.right = spacing / 2
        outRect.top = spacing / 2
        outRect.bottom = spacing / 2

        //Adjust top margin
        if (position < spanCount) {
            outRect.top = spacing
        }

        //Adjust left margin
        if (position % spanCount == 0) {
            outRect.left = spacing
        }

        //Adjust right margin
        if ((position + 1) % spanCount == 0) {
            outRect.right = spacing
        }

        //Adjust bottom margin
        if (position < count && position + spanCount >= count) {
            outRect.bottom = spacing
        }

    }
}