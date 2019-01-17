package abanob.prog.awlade.com.cunncry;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
   EditText editText1,editText3,editText4,editText5;
   ImageView alam1,alam3,alam4,alam5;
   TextView name1,name3,name4,name5;
   LinearLayout linear1,linear2,linear3,linear4;
    private   int test=1;
    private static final String TAG =  "currancyArrayList";
    List<Currancy> d;
    Button buttonOne,buttonTow,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,
    buttonEight,buttonNigh,buttonDote,buttonZero;
   ImageButton buttonClear;
    CurrencyPicker picker,picker2;
    ChackInternet chackInternet;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       chackInternet=new ChackInternet(getApplicationContext());
       if (!chackInternet.IsConnected())
       {
           Snackbar.make(findViewById(R.id.linearHome),"لم يوجد اتصال بالانترنت",Snackbar.LENGTH_LONG).show();
       }
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        Intil();
         picker = CurrencyPicker.newInstance("Select Currency");  // dialog title
         picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {

              }
        });
    }
    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            }
            else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available

                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        }
        catch (Exception e) { }
        return null;
    }
    private void Intil() {
        linear1=findViewById(R.id.linear1);
        linear2=findViewById(R.id.liner2);
        linear3=findViewById(R.id.linear3);
        linear4=findViewById(R.id.linear4);

        name1=findViewById(R.id.name1);
        name3=findViewById(R.id.name3);
        name4=findViewById(R.id.name4);
        name5=findViewById(R.id.name5);
        buttonOne=findViewById(R.id.buttonOne);
        buttonTow=findViewById(R.id.buttonTow);
        buttonThree=                    findViewById(R.id.buttonThree);
        buttonFour=findViewById(R.id.buttonFour);
        buttonFive=findViewById(R.id.buttonFive);
        buttonSix=findViewById(R.id.buttonSix);
        buttonSeven=findViewById(R.id.buttonSeven);
        buttonEight=findViewById(R.id.buttonEight);
        buttonNigh=findViewById(R.id.buttonNine);
        buttonDote=findViewById(R.id.buttonDot);
        buttonClear=findViewById(R.id.buttonClear);
        buttonZero=findViewById(R.id.buttonZero);
        editText1=findViewById(R.id.editText1);



        // name2=findViewById(R.id.name2);

        editText1.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                test=1;
                linear1.setBackgroundColor(getColor(R.color.cardview_shadow_start_color));
                linear2.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));
                linear3.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));
                linear4.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));

                return false;
            }
        });
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)&&test==1) {
                    Convert(editText3, name1.getText().toString() + "_" + name3.getText().toString(), Float.parseFloat(s.toString()));
                    Convert(editText4, name1.getText().toString() + "_" + name4.getText().toString(), Float.parseFloat(s.toString()));
                    Convert(editText5, name1.getText().toString() + "_" + name5.getText().toString(), Float.parseFloat(s.toString()));
                }
            }
        });
//        editText2=findViewById(R.id.editText2);
//        editText2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                test=2;
//                return false;
//            }
//        });
//        editText2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!TextUtils.isEmpty(s)&&test==2) {
//                    Convert(editText1, name2.getText().toString() + "_" + name1.getText().toString(), Float.parseFloat(editText2.getText().toString()));
//                    Convert(editText3, name2.getText().toString() + "_" + name3.getText().toString(), Float.parseFloat(editText2.getText().toString()));
//                    Convert(editText4, name2.getText().toString() + "_" + name4.getText().toString(), Float.parseFloat(editText2.getText().toString()));
//                    Convert(editText5, name2.getText().toString() + "_" + name5.getText().toString(), Float.parseFloat(editText2.getText().toString()));
//                }
//            }
//        });
        editText3=findViewById(R.id.editText3);
        editText3.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                test=3;
                linear2.setBackgroundColor(getColor(R.color.cardview_shadow_start_color));
                linear1.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));
                linear3.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));
                linear4.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));

                return false;
            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)&&test==3) {
                    Convert(editText1, name3.getText().toString() + "_" + name1.getText().toString(), Float.parseFloat(editText3.getText().toString()));
                    Convert(editText4, name3.getText().toString() + "_" + name4.getText().toString(), Float.parseFloat(editText3.getText().toString()));
                    Convert(editText5, name3.getText().toString() + "_" + name5.getText().toString(), Float.parseFloat(editText3.getText().toString()));
                }
           }
        });
        editText4=findViewById(R.id.editText4);
        editText4.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                test=4;
                linear3.setBackgroundColor(getColor(R.color.cardview_shadow_start_color));
                linear1.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));
                linear2.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));
                linear4.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));
                return false;
            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)&&test==4) {
                    Convert(editText3, name4.getText().toString() + "_" + name3.getText().toString(), Float.parseFloat(editText4.getText().toString()));
                    Convert(editText1, name4.getText().toString() + "_" + name1.getText().toString(), Float.parseFloat(editText4.getText().toString()));
                    Convert(editText5, name4.getText().toString() + "_" + name5.getText().toString(), Float.parseFloat(editText4.getText().toString()));
                }
            }
        });
        editText5=findViewById(R.id.editText5);
        editText5.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                test=5;
                linear4.setBackgroundColor(getColor(R.color.cardview_shadow_start_color));
                linear1.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));
                linear2.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));
                linear3.setBackgroundColor(getColor(R.color.cardview_shadow_end_color));

                return false;
            }
        });
        editText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)&&test==5) {
                    Convert(editText3, name5.getText().toString() + "_" + name3.getText().toString(), Float.parseFloat(s.toString()));
                    Convert(editText1, name5.getText().toString() + "_" + name1.getText().toString(), Float.parseFloat(s.toString()));
                    Convert(editText4, name5.getText().toString() + "_" + name4.getText().toString(), Float.parseFloat(s.toString()));
                }
                }
        });
        alam1=findViewById(R.id.alam1);
        name1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.setListener(new CurrencyPickerListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                    name1.setText(code);
                    alam1.setBackground(getResources().getDrawable(flagDrawableResID));
                    if(!TextUtils.isEmpty(editText1.getText().toString())) {
                        Convert(editText3, code + "_" + name3.getText().toString(), Float.parseFloat(editText1.getText().toString()));
                        Convert(editText4, code + "_" + name4.getText().toString(), Float.parseFloat(editText1.getText().toString()));
                        Convert(editText5, code + "_" + name5.getText().toString(), Float.parseFloat(editText1.getText().toString()));
                    }
                    picker.dismiss();


                    }
                });
                picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");

            }
        });

//        alam2=findViewById(R.id.alam2);
//        name2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                picker.setListener(new CurrencyPickerListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//                    @Override
//                    public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
//                        name2.setText(code);
//                        alam2.setBackground(getResources().getDrawable(flagDrawableResID));
//                      if(!TextUtils.isEmpty(editText2.getText().toString())) {
//                          Convert(editText1, code + "_" + name1.getText().toString(), Float.parseFloat(editText2.getText().toString()));
//                          Convert(editText3, code + "_" + name3.getText().toString(), Float.parseFloat(editText2.getText().toString()));
//                          Convert(editText4, code + "_" + name4.getText().toString(), Float.parseFloat(editText2.getText().toString()));
//                          Convert(editText5, code + "_" + name5.getText().toString(), Float.parseFloat(editText2.getText().toString()));
//                      }
//                        picker.dismiss();
//
//
//                    }
//                });
//                picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");
//
//            }
//        });
        alam3=findViewById(R.id.alam3);
        name3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.setListener(new CurrencyPickerListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                        name3.setText(code);
                        alam3.setBackground(getResources().getDrawable(flagDrawableResID));
                        if(!TextUtils.isEmpty(editText3.getText().toString())) {
                            Convert(editText1, code + "_" + name1.getText().toString(), Float.parseFloat(editText3.getText().toString()));
                            Convert(editText4, code + "_" + name4.getText().toString(), Float.parseFloat(editText3.getText().toString()));
                            Convert(editText5, code + "_" + name5.getText().toString(), Float.parseFloat(editText3.getText().toString()));
                        }
                        picker.dismiss();

                    }
                });
                picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");

            }
        });
        alam4=findViewById(R.id.alam4);
        name4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.setListener(new CurrencyPickerListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                        name4.setText(code);
                        alam4.setBackground(getResources().getDrawable(flagDrawableResID));
                        if(!TextUtils.isEmpty(editText4.getText().toString())) {
                            Convert(editText1, code + "_" + name1.getText().toString(), Float.parseFloat(editText4.getText().toString()));
                            Convert(editText3, code + "_" + name3.getText().toString(), Float.parseFloat(editText4.getText().toString()));
                            Convert(editText5, code + "_" + name5.getText().toString(), Float.parseFloat(editText4.getText().toString()));
                        }
                        picker.dismiss();

                    }
                });
                picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");

            }
        });
        alam5=findViewById(R.id.alam5);
        name5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.setListener(new CurrencyPickerListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                        name5.setText(code);
                        alam5.setBackground(getResources().getDrawable(flagDrawableResID));
                        if(!TextUtils.isEmpty(editText5.getText().toString())) {
                            Convert(editText1, code + "_" + name1.getText().toString(), Float.parseFloat(editText5.getText().toString()));
                            Convert(editText3, code + "_" + name3.getText().toString(), Float.parseFloat(editText5.getText().toString()));
                            Convert(editText4, code + "_" + name4.getText().toString(), Float.parseFloat(editText5.getText().toString()));
                        }
                        picker.dismiss();

                    }
                });
                picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");

            }
        });

    }

    private void getV(final String CodeConverter, final String code, final String name)
    {
         final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("جاري الحفظ .....");
        String url;
        url="http://free.currencyconverterapi.com/api/v3/convert?q="+CodeConverter+"&compact=ultra";
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                url                ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonResponse = new JSONObject(s);
                             float  val = (float) jsonResponse.getDouble(CodeConverter);
                             String S=String.format("%.5f",val);
                            d=lodeLastSearch();
                            d.add(new Currancy(code ,name,new Date(Calendar.getInstance().getTimeInMillis())+"",S));
                            Save(d);
                           Toast.makeText(MainActivity.this, "تم الحفظ", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);



        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinshed) {
            }
            public void onFinish() {
                if (progressDialog.isShowing() ) {
                    progressDialog.dismiss();
                    getV(CodeConverter,code,name);
                 }
            }
        }.start();


    }
    private void Convert(final EditText textView, final String CodeConverter, final float vl) {
        // https://free.currencyconverterapi.com/api/v6/convert?q=USD_PHP&compact=y

         String url;
        url="http://free.currencyconverterapi.com/api/v3/convert?q="+CodeConverter+"&compact=ultra";
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                url                ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonResponse = new JSONObject(s);
                            double val = jsonResponse.getDouble(CodeConverter);
                            float integerVal= (float) (val*vl);
                            String out=String.format("%.3f",integerVal);
                            textView.setText(out+"");
                           }
                        catch (JSONException e) {
                             e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
             }
        });
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
        stringRequest.setShouldCache(false);
//        new CountDownTimer(2000, 1000) {
//            public void onTick(long millisUntilFinshed) {
//            }
//            public void onFinish() {
//
//            }
//        }.start();



    }
    private void Save(List<Currancy> currencyMArrayList){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(currencyMArrayList);
        editor.putString(TAG, json);
        editor.commit();
    }
    private List<Currancy> lodeLastSearch() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString(TAG, "");
        Type type = new TypeToken<List<Currancy>>() {}.getType();
        List<Currancy> arrayList = gson.fromJson(json, type);
        if(arrayList==null)
        {
            return new ArrayList<Currancy>();
        }
        else {
             return arrayList;
        }
    }
    String SV;
    public void save1(View view) {
        picker = CurrencyPicker.newInstance("حفظ سعر التحويل من "+name1.getText().toString()+" الي ... ");  // dialog title
        Toast.makeText(this, "حفظ سعر التحويل من "+name1.getText().toString()+" الي ... ", Toast.LENGTH_SHORT).show();
        picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
         getV(name1.getText().toString()+"_"+code,code,name1.getText().toString());
            picker.dismiss();

            }
        });
        picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");

    }

    public void save2(View view) {
        picker = CurrencyPicker.newInstance("حفظ سعر التحويل من "+name3.getText().toString()+" الي ... ");  // dialog title
        Toast.makeText(this, "حفظ سعر التحويل من "+name3.getText().toString()+" الي ... ", Toast.LENGTH_SHORT).show();
        picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                getV(name3.getText().toString()+"_"+code,code,name3.getText().toString());
                picker.dismiss();

            }
        });
        picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");

    }

    public void save3(View view) {
        Toast.makeText(this, "حفظ سعر التحويل من "+name4.getText().toString()+" الي ... ", Toast.LENGTH_SHORT).show();
        picker = CurrencyPicker.newInstance("حفظ سعر التحويل من "+name4.getText().toString()+" الي ... ");  // dialog title
        picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                getV(name4.getText().toString()+"_"+code,code,name4.getText().toString());
                picker.dismiss();

            }
        });
        picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");

    }

    public void save4(View view) {
        Toast.makeText(this, "حفظ سعر التحويل من "+name5.getText().toString()+" الي ... ", Toast.LENGTH_SHORT).show();
        picker = CurrencyPicker.newInstance("حفظ سعر التحويل من "+name5.getText().toString()+" الي ... ");  // dialog title
        picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                 getV(name5.getText().toString()+"_"+code,code,name5.getText().toString());
                 picker.dismiss();
            }
        });
        picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            startActivity(new Intent(getApplicationContext(),About.class));
            return true;
        }
        if (id == R.id.clear) {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.remove(TAG).commit();
            Toast.makeText(this, "Clear Done..", Toast.LENGTH_SHORT).show();
             return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void store(View view) {
        startActivity(new Intent(getApplicationContext(),StoreData.class));
    }
   String from ;
    public void SaveRate(View view) {
        from="";
        picker = CurrencyPicker.newInstance("حفظ من");  // dialog title
         picker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                from=code;
                picker.dismiss();
                picker2 = CurrencyPicker.newInstance("  الي  "+from+"حفظ من ");  // dialog title
                picker2.setListener(new CurrencyPickerListener() {
                    @Override
                    public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                        getV(from+"_"+code,code,from);
                        picker2.dismiss();
                    }
                });
                picker2.show(getSupportFragmentManager(), "CURRENCY_PICKER");
            }
        });
        picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");

    }
}
