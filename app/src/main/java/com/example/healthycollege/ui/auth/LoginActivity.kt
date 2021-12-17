package com.example.healthycollege.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.healthycollege.R
import com.example.healthycollege.data.model.EmailDTO
import com.example.healthycollege.data.service.UserApiService
import com.example.healthycollege.data.service.rest.RestApiService.Companion.instance
import com.example.healthycollege.databinding.ActivityLoginBinding
import com.example.healthycollege.ui.MainActivity
import com.example.healthycollege.utils.MyApplication
import com.example.healthycollege.utils.NetworkConnection
import com.example.healthycollege.utils.NetworkStatus
import com.example.healthycollege.utils.toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), AuthListener {

    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel : AuthViewModel
    lateinit var viewModelFactory: AuthViewModelFactory
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initViewModel()

        val connection = NetworkConnection(applicationContext)
        connection.observe(this){ isConnected ->
            NetworkStatus.status = isConnected
        }
    }

    private fun initViewModel() {
        viewModelFactory = AuthViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)
        viewModel.authSignInListener = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.signInResponse.observe(this) {
            Log.d("로그인", it)
            if (it.equals("true")) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.d("LoginActivity", "로그인 실패")
                toast("이메일과 비밀번호를 다시 확인해주세요.")
            }
        }

    }

    override fun onStarted() {}
    override fun onSuccess() {}

    override fun onFailure(message: String, type: Int) {
        when(type){
            0 -> {
                binding.loginEmail.requestFocus()
            }
            1 -> {
                binding.loginPassword.requestFocus()
            }
        }
        toast(message)
    }
}