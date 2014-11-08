package mmpud.project.daycountwidget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

import mmpud.project.daycountwidget.util.Utils;
import timber.log.Timber;

/**
 * Created by george on 2014/11/3.
 */
public class SelectStyleAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private int mResource;
    private List<String> mStringList = null;

    public SelectStyleAdapter(Context context, int resource, List<String> stringList) {
        super(context, resource, stringList);
        mContext = context;
        mResource = resource;
        mStringList = stringList;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        CounterLayoutHolder holder;

        if (view == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            view = inflater.inflate(mResource, parent, false);

            holder = new CounterLayoutHolder();
            holder.ivStyle = (ImageView) view.findViewById(R.id.list_item_style);
            view.setTag(holder);
        } else {
            holder = (CounterLayoutHolder) view.getTag();
        }
        String strStyle = mStringList.get(position);

        Timber.d("style: " + strStyle);
        int resourceIDStyle = mContext.getResources().getIdentifier(strStyle + "_config", "drawable", "mmpud.project.daycountwidget");
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), resourceIDStyle);
        if(bitmap != null) {
            holder.ivStyle.setImageBitmap(Utils.getRoundedCornerBitmap(bitmap, 40));
        }
        return view;
    }

    private static class CounterLayoutHolder {
        ImageView ivStyle;
    }

}
