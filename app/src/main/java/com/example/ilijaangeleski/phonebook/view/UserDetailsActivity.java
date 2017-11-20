package com.example.ilijaangeleski.phonebook.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilijaangeleski.phonebook.R;
import com.example.ilijaangeleski.phonebook.model.User;
import com.example.ilijaangeleski.phonebook.presenter.UserDetailsPresenter;
import com.example.ilijaangeleski.phonebook.util.CircleTransform;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilija Angeleski on 11/20/2017.
 */

public class UserDetailsActivity extends AppCompatActivity implements UserDetailsView {
    @BindView(R.id.profileImage)
    ImageView profileImage;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.firstName)
    TextView firstName;

    @BindView(R.id.lastName)
    TextView lastName;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.streetinfo)
    TextView streetInfo;

    @BindView(R.id.callImage)
    ImageView callImage;

    @BindView(R.id.smsImage)
    ImageView smsImage;

    @BindView(R.id.nationality)
    TextView nationality;

    @BindView(R.id.phoneNumber)
    TextView phoneNumber;

    @BindView(R.id.postcode)
    TextView postcodeInfo;

    private User user = null;
    UserDetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_activity);
        ButterKnife.bind(this);

        presenter = new UserDetailsPresenter(this);
        user = (User) getIntent().getExtras().getSerializable("clickedUser");

        initVariables();
        initListeners();

    }

    public void initVariables() {
        Picasso.with(this).load(user.getPicture().getLarge()).
                transform(new CircleTransform()).placeholder(R.mipmap.ic_profile).
                error(R.mipmap.ic_profile).fit().into(profileImage);

        title.setText(user.getName().getTitle());
        firstName.setText(user.getName().getFirst());
        lastName.setText(user.getName().getLast());
        username.setText(user.getLogin().getUsername());
        streetInfo.setText(presenter.getStreetInfo(user.getLocation()));

        postcodeInfo.setText(user.getLocation().getPostcode());
        nationality.setText(user.getNat());
        phoneNumber.setText(user.getPhone());
        email.setText(user.getEmail());
    }
    public void initListeners() {
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        streetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });
        callImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }

        });
        smsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSms();
            }
        });
    }


    public void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{user.getEmail()});
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
        startActivity(Intent.createChooser(intent, ""));
    }

    public void openMap() {
        String map = "http://maps.google.co.in/maps?q=" + presenter.getStreetInfo(user.getLocation());
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(map));
        startActivity(intent);
    }

    public void call() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + user.getPhone()));
            startActivity(intent);
            return;
        }
    }

    public void sendSms() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", user.getPhone(), null)));
    }
}
