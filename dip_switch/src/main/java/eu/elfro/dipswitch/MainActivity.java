package eu.elfro.dipswitch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import eu.elfro.dipswitch.UTIL.PROCKI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText liczba;
    TextView b0, b1, b2, b3, b4, b5, b6, b7, t0, t7;
    ImageView i0, i1, i2, i3, i4, i5, i6, i7;
    TextView dec, hex, bin;
    RadioButton dip, jmp;
    LinearLayout l1, l2, l3, l4, l5;

    Button plus, minus, zero;
    boolean dipSwitch = true;
    boolean use_mask = false;
    boolean use_add = false;
    boolean isAnd = false;

    CheckBox mask, add;
    EditText Emask, Eadd;
    Switch aSwitch;

    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            changeText();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        PROCKI.kontekst = this.getApplicationContext();
        liczba = findViewById(R.id.editTextLiczba);
        liczba.addTextChangedListener(tw);

        Emask = findViewById(R.id.editTextMask);
        Eadd = findViewById(R.id.editTextAdd);

        Emask.addTextChangedListener(tw);
        Eadd.addTextChangedListener(tw);

        mask = findViewById(R.id.checkBoxMask);
        add = findViewById(R.id.checkBoxAdd);

        mask.setOnClickListener(this);
        add.setOnClickListener(this);

        b0 = findViewById(R.id.textViewB0);
        b1 = findViewById(R.id.textViewB1);
        b2 = findViewById(R.id.textViewB2);
        b3 = findViewById(R.id.textViewB3);
        b4 = findViewById(R.id.textViewB4);
        b5 = findViewById(R.id.textViewB5);
        b6 = findViewById(R.id.textViewB6);
        b7 = findViewById(R.id.textViewB7);

        dec = findViewById(R.id.textViewDEC);
        hex = findViewById(R.id.textViewHEX);
        bin = findViewById(R.id.textViewBIN);

        i0 = findViewById(R.id.imageViewB0);
        i1 = findViewById(R.id.imageViewB1);
        i2 = findViewById(R.id.imageViewB2);
        i3 = findViewById(R.id.imageViewB3);
        i4 = findViewById(R.id.imageViewB4);
        i5 = findViewById(R.id.imageViewB5);
        i6 = findViewById(R.id.imageViewB6);
        i7 = findViewById(R.id.imageViewB7);

        context = this;
        i0.setOnTouchListener(touchListener);
        i1.setOnTouchListener(touchListener);
        i2.setOnTouchListener(touchListener);
        i3.setOnTouchListener(touchListener);
        i4.setOnTouchListener(touchListener);
        i5.setOnTouchListener(touchListener);
        i6.setOnTouchListener(touchListener);
        i7.setOnTouchListener(touchListener);

        plus = findViewById(R.id.buttonPlus);
        minus = findViewById(R.id.buttonMinus);
        zero = findViewById(R.id.button0);

        zero.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);

        dip = findViewById(R.id.radioButtonDIP);
        jmp = findViewById(R.id.radioButtonJMP);

        dip.setOnClickListener(this);
        jmp.setOnClickListener(this);

        l1 = findViewById(R.id.linearLayoutDIP);
        l2 = findViewById(R.id.linearLayoutNR);
        l3 = findViewById(R.id.linearLayoutBIT);
        l4 = findViewById(R.id.linearLayoutDEC);
        l5 = findViewById(R.id.linearLayoutTOP);
        t0 = findViewById(R.id.textViewT0);
        t7 = findViewById(R.id.textViewT7);

        aSwitch = findViewById(R.id.switchAND);
        aSwitch.setOnClickListener(this);
        isAnd = PROCKI.getBoolValue("isAnd", false);
        aSwitch.setChecked(isAnd);


        dipSwitch = PROCKI.getBoolValue("dips", dipSwitch);

        use_mask = PROCKI.getBoolValue("Use_mask", false);
        mask.setChecked(use_mask);
        use_add = PROCKI.getBoolValue("Use_add", false);
        add.setChecked(use_add);

        if (dipSwitch) dip.setChecked(true);
        else
            jmp.setChecked(true);
        setBackgr();

        Emask.setText(PROCKI.getStringValue("mask", "0"));
        Eadd.setText(PROCKI.getStringValue("add", "0"));
        setLiczba(PROCKI.getStringValue("liczba", "0"));
        //kalkuluj(0);
    }

    static Context context;

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_UP) {

                int nl = 0;
                try {
                    String s = liczba.getText().toString();
                    if (s == null || s == "") s = "0";
                    nl = Integer.parseInt(s);

                    if (nl > 255) nl = 255;
                    if (nl < 0) nl = 0;

                    int bit = 0;
                    switch (view.getId()) {
                        case R.id.imageViewB0:
                            bit = 0;
                            break;
                        case R.id.imageViewB1:
                            bit = 1;
                            break;
                        case R.id.imageViewB2:
                            bit = 2;
                            break;
                        case R.id.imageViewB3:
                            bit = 3;
                            break;
                        case R.id.imageViewB4:
                            bit = 4;
                            break;
                        case R.id.imageViewB5:
                            bit = 5;
                            break;
                        case R.id.imageViewB6:
                            bit = 6;
                            break;
                        case R.id.imageViewB7:
                            bit = 7;
                            break;
                    }


                    boolean set = false;
                    if (dipSwitch) {
                        float y = event.getY();
                        float h = view.getHeight();
                        if (y < h / 2) set = true;
                    } else {
                        //event.
                        set = ((nl >> bit) & 1) != 1;

                    }

                    if (set) {
                        nl |= 1 << bit;
                    } else {
                        nl &= ~(1 << bit);
                    }
                    kalkuluj(nl);

                } catch (Exception ex) {
                }
            }
            return true;

        }
    };

    String addZeros(String s,int ile)
    {
        while (s.length()<ile) s="0"+s;
        return s;
    }

    @SuppressLint("SetTextI18n")
    private int kalkuluj(int nl) {

        //  if ()
        String s = "";
        if (use_add) s = " "+ getString(R.string.addedNumber);
        if (use_mask) s = " "+ getString(R.string.addedMask);
        if (use_add && use_mask) s = " "+ getString(R.string.addedNumberAndMask);

        dec.setText(getString(R.string.liczba_dec) + s + ": " + nl);
        hex.setText(getString(R.string.liczba_hex) + s + ": " + addZeros(Integer.toString(nl, 16).toUpperCase(),2));
        bin.setText(getString(R.string.liczba_bin) + s + ": " + addZeros(Integer.toString(nl, 2),8));

        if (!locktextChangesLoop)
        {

            setLiczba(nl);

        }
        return nl;
    }


    boolean locktextChangesLoop = false;

    void setLiczba(int nr)
    {
        setLiczba(Integer.toString(nr));
    }

    void setLiczba(String s)
    {

        liczba.setText(s);
        liczba.setSelection(s.length(), s.length());
    }

    private void changeText() {
        String sL = liczba.getText().toString();

        if (locktextChangesLoop) return;
        locktextChangesLoop = true;
        if (PROCKI.stringIsNullOrEmpty(sL))
        {
            sL="0";
            setLiczba("0");

        }
        else
        {
            while (sL.startsWith("0") && !sL.equalsIgnoreCase("0"))
            {
                sL= sL.substring(1);
                setLiczba(sL);

            }
        }

        if (sL == null || sL == "")
        {
            locktextChangesLoop = false;
            return;
        }

        int nl = 0;

        try {
            nl = Integer.parseInt(sL);
            if (nl > 255) {
                nl = 255;
                nl = kalkuluj(nl);
                setLiczba(Integer.toString(nl));

            }
            if (nl < 0) {
                nl = 0;
                nl = kalkuluj(nl);
                setLiczba(Integer.toString(nl));
            }



            if (use_add) {
                try {
                    String sm = Eadd.getText().toString();
                    if (sm != null && sm != "") {
                        int add = Integer.parseInt(sm);
                        if (add > 255) add = 255;
                        if (add < 0) add = 0;
                        nl = nl + add;
                    }
                } catch (Exception ex) {
                    Toast.makeText(this,  getString(R.string.EnterCorrectNumber), Toast.LENGTH_SHORT).show();
                }
            }

            if (nl < 0) nl = 0;
            if (nl > 255) nl = 255;

            if (use_mask) {
                try {
                    String sm = Emask.getText().toString();
                    if (sm != null && sm != "") {
                        int maska = Integer.parseInt(sm);
                        if (maska > 255) maska = 255;
                        if (maska < 0) maska = 0;
                        if (isAnd)
                            nl = nl & maska;
                        else nl = nl | maska;
                    }
                } catch (Exception ex) {
                    Toast.makeText(this, getString(R.string.EnterCorrectMask), Toast.LENGTH_SHORT).show();
                }
            }

            if (nl < 0) nl = 0;
            if (nl > 255) nl = 255;

            sets(b0, i0, (nl & 1) == 1);
            sets(b1, i1, (nl & 2) == 2);
            sets(b2, i2, (nl & 4) == 4);
            sets(b3, i3, (nl & 8) == 8);
            sets(b4, i4, (nl & 16) == 16);
            sets(b5, i5, (nl & 32) == 32);
            sets(b6, i6, (nl & 64) == 64);
            sets(b7, i7, (nl & 128) == 128);
            kalkuluj(nl);

        } catch (Exception ex) {
            Toast.makeText(this, getString(R.string.EnterCorrectNumber), Toast.LENGTH_SHORT).show();
        }
        locktextChangesLoop = false;

    }

    private void sets(TextView bit, ImageView Ibit, boolean b) {
        if (b) {
            bit.setText("1");
            if (dipSwitch)
                Ibit.setImageResource(R.drawable.jeden);
            else
                Ibit.setImageResource(R.drawable.jeden1);
        } else {
            bit.setText("0");
            if (dipSwitch)
                Ibit.setImageResource(R.drawable.zero);
            else
                Ibit.setImageResource(R.drawable.zero1);
        }


    }

    @Override
    public void onClick(View view) {
        if (view == plus) addLiczba(1);
        if (view == minus) addLiczba(-1);

        if (view == zero) setLiczba("0");

        if (view == dip) {
            dipSwitch = true;
            setBackgr();
            changeText();
            saveSetting();
        }
        if (view == jmp) {
            dipSwitch = false;
            setBackgr();
            changeText();
            saveSetting();
        }

        if (view == mask) {
            use_mask = mask.isChecked();
            changeText();
            saveSetting();
        }

        if (view == add) {
            use_add = add.isChecked();
            changeText();
            saveSetting();
        }

        if (view == aSwitch) {
            isAnd = aSwitch.isChecked();
            changeText();
            saveSetting();
        }


    }

    @Override
    protected void onPause() {
        saveSetting();
        super.onPause();
    }

    @Override
    protected void onStop() {
        saveSetting();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        saveSetting();
        super.onDestroy();
    }

    private void saveSetting() {
        try {
            PROCKI.setValue("isAnd", isAnd);

            PROCKI.setValue("dips", dipSwitch);
            PROCKI.setValue("Use_mask", use_mask);
            PROCKI.setValue("Use_add", use_add);

            PROCKI.setValue("liczba", liczba.getText().toString());
            PROCKI.setValue("mask", Emask.getText().toString());
            PROCKI.setValue("add", Eadd.getText().toString());
        } catch (Exception ex) {
        }

    }

    private void setBackgr() {
        if (dipSwitch) {
            l1.setBackgroundColor(ContextCompat.getColor(this, R.color.dip));
            l2.setBackgroundColor(ContextCompat.getColor(this, R.color.dip));
            l3.setBackgroundColor(ContextCompat.getColor(this, R.color.dip));
            l4.setBackgroundColor(ContextCompat.getColor(this, R.color.dip));
            l5.setBackgroundColor(ContextCompat.getColor(this, R.color.dip));
            t0.setVisibility(View.VISIBLE);
            t7.setVisibility(View.VISIBLE);

        } else {
            l1.setBackgroundColor(ContextCompat.getColor(this, R.color.jmp));
            l2.setBackgroundColor(ContextCompat.getColor(this, R.color.jmp));
            l3.setBackgroundColor(ContextCompat.getColor(this, R.color.jmp));
            l4.setBackgroundColor(ContextCompat.getColor(this, R.color.jmp));
            l5.setBackgroundColor(ContextCompat.getColor(this, R.color.jmp));
            t0.setVisibility(View.INVISIBLE);
            t7.setVisibility(View.INVISIBLE);
        }


    }

    private void addLiczba(int i) {
        int nl = 0;
        try {
            String s = liczba.getText().toString();
            if (s == null || s == "") s = "0";
            nl = Integer.parseInt(s);
            nl += i;
            if (i == 0) nl = 0;
            if (nl > 255) nl = 255;
            if (nl < 0) nl = 0;
            kalkuluj(nl);
        } catch (Exception ex) {
            Toast.makeText(this.getApplicationContext(), getString(R.string.EnterCorrectNumber), Toast.LENGTH_SHORT).show();
        }


    }
}
