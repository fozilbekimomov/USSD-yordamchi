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
public class CardsPagerTransformerBasic implements ViewPager.PageTransformer {
    private final int baseElevation;
    private final int raisingElevation;
    private final float smallerScale;

    public CardsPagerTransformerBasic(int baseElevation, int raisingElevation, float smallerScale) {
        this.baseElevation = baseElevation;
        this.raisingElevation = raisingElevation;
        this.smallerScale = smallerScale;
    }

    @Override
    public void transformPage(View page, float position) {
        float absPosition = Math.abs(position);
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