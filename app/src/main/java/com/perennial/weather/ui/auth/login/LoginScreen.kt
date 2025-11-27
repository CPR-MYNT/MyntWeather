package com.perennial.weather.ui.auth.login


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.perennial.weather.R

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
    ,navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit) {
    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val isLoading by loginViewModel.isLoading.collectAsState()
    val message by loginViewModel.message.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text(
            text = navController.context.resources.getString(R.string.welcome_text),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {loginViewModel.onEmailChange(it)},
            label = {Text(navController.context.resources.getString(R.string.email))},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {loginViewModel.onPasswordChange(it)},
            label = {Text(navController.context.resources.getString(R.string.password))},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                loginViewModel.onLoginClick(navController = navController,
                    onSuccess = {
                        Toast.makeText(navController.context, "Login Success", Toast.LENGTH_SHORT).show()
                        //navigateToHome()
                    }
                )},
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = !isLoading

        ) {
            if (isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.width(22.dp),
                    color = Color.Black,
                    strokeWidth = 2.dp
                )
            }else{
                Text(
                    text = navController.context.resources.getString(R.string.login),
                    fontSize = 16.sp,
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "${navController.context.resources.getString(R.string.dont_have_account)} ")

            Text(
                text = navController.context.resources.getString(R.string.register),
                color = Color.Blue,
                modifier = Modifier.clickable{
                    navigateToRegister()
                }
            )
        }

        if (message.isNotEmpty()){
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = message, color = MaterialTheme.colorScheme.error)
        }

    }

}
