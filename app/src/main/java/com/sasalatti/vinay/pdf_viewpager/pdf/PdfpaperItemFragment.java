package com.sasalatti.vinay.pdf_viewpager.pdf;

/**
 * Created by vinay on 5/24/16.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.sasalatti.vinay.pdf_viewpager.R;
import java.io.File;

/**
 * Created by vinay on 5/19/16.
 */
public class PdfpaperItemFragment extends Fragment {


    private static Context mContext;
    ProgressBar progressBar;


    public PdfpaperItemFragment() {
    }

    public static Fragment newInstance(String path, Context context) {
        mContext = context;
        Bundle args = new Bundle();
        args.putString("path", path);
        PdfpaperItemFragment fragment = new PdfpaperItemFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pdf_paper_container, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        initPaperView(view);


        return view;
    }

    private void initPaperView(View view) {

        PDFView photoView;
        File myDir;
        File pdf;
        String FILE = null;
        ImageView ad;


        photoView = (PDFView) view.findViewById(R.id.paper);
        ad = (ImageView) view.findViewById(R.id.ad);


        progressBar.setVisibility(View.VISIBLE);

        FILE = getTitle();

        myDir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/saved-pdfs");
//        File myDir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/pb_pdf");

        pdf = new File(myDir, FILE);

//        PdfContext pdfc = new PdfContext();
//        CodecDocument pdfd = pdfc.openDocument(pdf.getAbsolutePath());
//        PdfPage pdfp = (PdfPage) pdfd.getPage(0);
//        Bitmap page = pdfp.renderBitmap(521,1024,new RectF(0, 0, 1, 1));
//        if(page != null){
//
//        }

//        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());


        photoView.fromFile(pdf)
                .pages(0)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(false)
                .swipeVertical(false)
                .enableSwipe(true)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                    }
                })
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        progressBar.setVisibility(View.GONE);
                    }
                })
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {

                    }
                })
                .load();

        Uri uriAd = Uri.parse("any valid image url ");

        Glide.with(this).load(uriAd).asBitmap().into(ad);


    }


    public String getTitle() {
        return getArguments().getString("path");
    }

    public int getPosition() {
        return getArguments().getInt("position");
    }


}

