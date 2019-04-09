package online.daniilk.moneytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    private static final String TAG = "AddItemActivity";
    public static final String TYPE_KEY = "type";

    private EditText name;
    private EditText price;
    private Button addBtn;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        //setTitle(R.string.add_item_title);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.add_item_screen_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        addBtn = findViewById(R.id.add_btn);

        type = getIntent().getStringExtra(TYPE_KEY);

        addBtn.setEnabled(false);

        TextWatcher txtWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (price.getText() == charSequence) {
                  //  Toast.makeText(AddItemActivity.this, "Price ID", Toast.LENGTH_SHORT).show();
                    if (TextUtils.isEmpty(price.getText()))
                        price.setText(getString(R.string.price));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                addBtn.setEnabled(!TextUtils.isEmpty(name.getText()) && !TextUtils.isEmpty(price.getText()));

            }
        };

        name.addTextChangedListener(txtWatcher);
        price.addTextChangedListener(txtWatcher);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = name.getText().toString();
                String priceValue = price.getText().toString();

                Item item = new Item(nameValue, priceValue, type);

                Intent intent = new Intent();
                intent.putExtra("item", item);
                // сирилизация данных (в байты)

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
