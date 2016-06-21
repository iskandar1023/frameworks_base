package com.android.systemui.screenshot;

/*
* Copyright (C) 2016 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/

import android.content.Context;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class PenSizeArrayAdapter extends ArrayAdapter<String> {
    private String[] mPenSizes;
    private int viewSize;

    public PenSizeArrayAdapter(Context context, int resourceId, String[] penSizes, int viewSize) {
        super(context, resourceId, penSizes);
        this.mPenSizes = penSizes;
        this.viewSize = viewSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createPenSizeImage(position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createPenSizeImage(position);
    }

    private ImageView createPenSizeImage(int position){
        ImageView imageView = new ImageView(getContext());
        final int contentWidth = viewSize - 8;
        final int penSizeValue = Integer.valueOf(mPenSizes[position]);
        final float density = getContext().getResources().getDisplayMetrics().density;
        final int penSize = Math.round(penSizeValue * density);

        final Canvas canvas = new Canvas();
        canvas.setDrawFilter(new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG,Paint.FILTER_BITMAP_FLAG));
        final Bitmap bmp = Bitmap.createBitmap(contentWidth, contentWidth, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bmp);
        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(penSize);
        canvas.drawLine(8, contentWidth / 2, contentWidth, contentWidth / 2, paint);

        imageView.setImageDrawable(new BitmapDrawable(getContext().getResources(), bmp));
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ScaleType.CENTER);
        return imageView;
    }
}
