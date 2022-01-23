package com.openclassrooms.firebaseoc.ui;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseUser;
import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.databinding.ActivityProfileBinding;
import com.openclassrooms.firebaseoc.ui.manager.UserManager;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding> {

    private UserManager userManager = UserManager.getInstance();

    @Override
    ActivityProfileBinding getViewBinding() {
        return ActivityProfileBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupListeners();
        updateUIWithUserData();
    }


    private void setupListeners(){

        // Notif checkbox
        binding.NotifCheckBox.setOnCheckedChangeListener((compoundButton, checked) -> {
            userManager.updateNotif(checked);
        });

        // Update button
        binding.updateButton.setOnClickListener(view -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            userManager.updateUsername(binding.usernameEditText.getText().toString())
                    .addOnSuccessListener(aVoid -> {
                        binding.progressBar.setVisibility(View.INVISIBLE);
                    });
        });

        // Delete button
        binding.deleteButton.setOnClickListener(view -> {

            new AlertDialog.Builder(this)
                    .setMessage(R.string.popup_message_confirmation_delete_account)
                    .setPositiveButton(R.string.popup_message_choice_yes, (dialogInterface, i) ->
                            userManager.deleteUser(ProfileActivity.this)
                                    .addOnSuccessListener(aVoid -> {
                                                finish();
                                            }
                                    )
                    )
                    .setNegativeButton(R.string.popup_message_choice_no, null)
                    .show();

        });
    }

    private void updateUIWithUserData(){
        if(userManager.isCurrentUserLogged()){
            FirebaseUser user = userManager.getCurrentUser();

            if(user.getPhotoUrl() != null){
                setProfilePicture(user.getPhotoUrl());
            }
            setTextUserData(user);
        }
    }

    private void setProfilePicture(Uri profilePictureUrl){
        Glide.with(this)
                .load(profilePictureUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.profileImageView);
    }

    private void setTextUserData(FirebaseUser user){

        //Get email & username du User
        String email = TextUtils.isEmpty(user.getEmail()) ? getString(R.string.info_no_email_found) : user.getEmail();
        String username = TextUtils.isEmpty(user.getDisplayName()) ? getString(R.string.info_no_username_found) : user.getDisplayName();

        //Update views
        binding.usernameEditText.setText(username);
        binding.emailTextView.setText(email);
    }

    private void getUserData(){
        userManager.getUserData().addOnSuccessListener(user -> {
            String username = TextUtils.isEmpty(user.getUsername()) ? getString(R.string.info_no_username_found) : user.getUsername();
            binding.NotifCheckBox.setChecked(user.getNotif());
            binding.usernameEditText.setText(username);
        });
    }

}
