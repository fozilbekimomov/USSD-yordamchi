package uz.fozilbekimomov.ussdyordamchi.core.list.helpers;

import android.os.Build;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/24/20
 * @project USSD yordamchi
 */
public class CardsPagerTransformerShift implements ViewPager.PageTransformer {

    private int baseElevation;
    private int raisingElevation;
    private float smallerScale;
    private float startOffset;

    public CardsPagerTransformerShift(int baseElevation, int raisingElevation, float smallerScale, float startOffset) {
        this.baseElevation = baseElevation;
        this.raisingElevation = raisingElevation;
        this.smallerScale = smallerScale;
        this.startOffset = startOffset;
    }

    @Override
    public void transformPage(View page, float position) {
        float absPosition = Math.abs(position - startOffset);

        if (absPosition >= 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                page.setElevation(baseElevation);
            }
            page.setScaleY(smallerScale);
        } else {
            // This will be during transformation
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                page.setElevation(((1 - absPosition) * raisingElevation + baseElevation));
            }
            page.setScaleY((smallerScale - 1) * absPosition + 1);
        }
    }

}