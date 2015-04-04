
package com.haja.tionaApp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements ActionBar.TabListener {

//    @ViewById(R.id.grid_btn)
//    GridView gridButton;

    private String pdfName;
    public String[] buttonsNames ={"1","2","3","4","5","6","7","8","9","OK"};

    @AfterViews
    void afterViews() {
//        gridButton.setAdapter(new ButtonAdapter(this));
//        gridButton.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView<?> parent,
//                                    View v, int position, long id){
//                Toast.makeText(getBaseContext(),
//                        "pic" + (position + 1) + " selected",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Click(R.id.btn_test)
    public void btnClick(){
        Toast.makeText(getApplicationContext(), "vous avez appuyer sur le bouton, vous savez lire...", Toast.LENGTH_SHORT).show();
    }

    @Click(R.id.btn_16)
    public void btn16(){
        pdfName = "16.pdf";
        showPdf(pdfName);
    }

    @Click(R.id.btn_428)
    public void btn428(){
        pdfName = "428.pdf";
        showPdf(pdfName);
    }

    @Click(R.id.btn_512)
    public void btn512(){
        pdfName = "512.pdf";
        showPdf(pdfName);
    }

    @Click(R.id.btn_ff16)
    public void btnFF16(){
        pdfName = "ff16.pdf";
        showPdf(pdfName);
    }

    @Click(R.id.btn_261)
    public void btn261(){
        pdfName = "261.pdf";
        showPdf(pdfName);
    }

    @Click(R.id.btn_antema1)
    public void btnAntema1(){
        pdfName = "antema1.pdf";
        showPdf(pdfName);
    }

    @Click(R.id.btn_1)
    public void btn1(){
        pdfName = "1.pdf";
        showPdf(pdfName);
    }

    @Click(R.id.btn_115)
    public void btn115(){
        pdfName = "115.pdf";
        showPdf(pdfName);
    }

    @Click(R.id.btn_266)
    public void btn266(){
        pdfName = "266.pdf";
        showPdf(pdfName);
    }

    @Click(R.id.btn_122)
    public void btn122(){
        pdfName = "642.pdf";
        showPdf(pdfName);
    }

    public void showPdf(String name){
        Intent i = new Intent(this, PdfActivity_.class);
        i.putExtra("fileName", pdfName);
        this.startActivity(i);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

//    public class ButtonAdapter extends BaseAdapter {
//        private Context context;
//        public ButtonAdapter(Context c){
//            context = c;
//        }
//        public int getCount() {
//            return buttonsNames.length;
//        }
//        public Object getItem(int position) {
//            return position;
//        }
//        public long getItemId(int position) {
//            return position;
//        }
//        public View getView(int position, View convertView, ViewGroup parent){
//            Button btn;
//            if (convertView == null) {
//                btn = new Button(context);
//                btn.setLayoutParams(new GridView.LayoutParams(100, 100));
//                btn.setPadding(8, 8, 8, 8);
//                btn.setFocusable(false);
//                btn.setClickable(false);
//            }else {
//                btn = (Button) convertView;
//            }
//            btn.setText(buttonsNames[position]);
//            btn.setTextColor(Color.WHITE);
//            btn.setId(position);
//            return btn;
//        }
//    }

}
