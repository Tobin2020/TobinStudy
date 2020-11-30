package top.tobin.login

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }


    override fun onSupportNavigateUp() =
        findNavController(this, R.id.login_nav_host_fragment).navigateUp()


    override fun onBackPressed() {
        super.onBackPressed()
    }
}