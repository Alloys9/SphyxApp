package com.sphyxx.Sphyx

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sphyxx.Sphyx.databinding.ActivitySettingsBinding

class settings : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var user:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        if (user.currentUser != null){
            user.currentUser?.let {
            }
        }
        //Logging out
        binding.logoutbtn.setOnClickListener {
            user.signOut()
            startActivity(
                Intent(this, SignInActivity::class.java )
            )
            finish()
        }
        //Deleting the Account
        binding.deletebtn.setOnClickListener {
            val user = Firebase.auth.currentUser
            user?.delete()?.addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(this, "Account Successfully Deleted", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
