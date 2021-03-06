//
// DO NOT EDIT THIS FILE.Generated using AndroidAnnotations 3.3.2.
//  You can create a larger work that contains this file and distribute that work under terms of your choice.
//


package com.haja.tionaApp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.haja.tionaApp.R.id;
import com.haja.tionaApp.R.layout;
import com.joanzapata.pdfview.PDFView;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PdfActivity_
    extends PdfActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.activity_pdf);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        afterInject();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static PdfActivity_.IntentBuilder_ intent(Context context) {
        return new PdfActivity_.IntentBuilder_(context);
    }

    public static PdfActivity_.IntentBuilder_ intent(Fragment fragment) {
        return new PdfActivity_.IntentBuilder_(fragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        btnLiturgie = ((Button) hasViews.findViewById(id.btn_liturgique));
        editText = ((EditText) hasViews.findViewById(id.nb_edit));
        pdfView = ((PDFView) hasViews.findViewById(id.pdfview));
        btnFF = ((Button) hasViews.findViewById(id.btn_fihirana_fanampiny));
        btnAntema = ((Button) hasViews.findViewById(id.btn_antema));
        btnFihirana = ((Button) hasViews.findViewById(id.btn_fihirana));
        searchType = ((TextView) hasViews.findViewById(id.search_type));
        btnChorale = ((Button) hasViews.findViewById(id.btn_chorale));
        {
            View view = hasViews.findViewById(id.btn_go);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        PdfActivity_.this.go();
                    }

                }
                );
            }
        }
        if (btnChorale!= null) {
            btnChorale.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    PdfActivity_.this.showChoraleSongs();
                }

            }
            );
        }
        afterViews();
    }

    public static class IntentBuilder_
        extends ActivityIntentBuilder<PdfActivity_.IntentBuilder_>
    {

        private Fragment fragment_;

        public IntentBuilder_(Context context) {
            super(context, PdfActivity_.class);
        }

        public IntentBuilder_(Fragment fragment) {
            super(fragment.getActivity(), PdfActivity_.class);
            fragment_ = fragment;
        }

        @Override
        public void startForResult(int requestCode) {
            if (fragment_!= null) {
                if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
                    fragment_.startActivityForResult(intent, requestCode, lastOptions);
                } else {
                    fragment_.startActivityForResult(intent, requestCode);
                }
            } else {
                if (context instanceof Activity) {
                    Activity activity = ((Activity) context);
                    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
                        activity.startActivityForResult(intent, requestCode, lastOptions);
                    } else {
                        activity.startActivityForResult(intent, requestCode);
                    }
                } else {
                    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
                        context.startActivity(intent, lastOptions);
                    } else {
                        context.startActivity(intent);
                    }
                }
            }
        }

    }

}
