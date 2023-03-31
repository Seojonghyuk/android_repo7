package com.example.intentpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intentpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCallActivity2.setOnClickListener {
            //명시적 인텐트
            val intent: Intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("name", "홍길동")
            intent.putExtra("age", 27)
            startActivity(intent)
        }


        binding.btnCallActivity3.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("name", "이정호")
            intent.putExtra("age", 100)
            startActivity(intent)
        }
        binding.btnCallActivity4.setOnClickListener {
            //명시적 인텐트
            val intent: Intent = Intent(this, MainActivity4::class.java)
            intent.putExtra("x", 45)
            intent.putExtra("y", 23)
            intent.putExtra("operator", "+")
            startActivityForResult(intent, 20)

        }


        binding.btnCallActivity5.setOnClickListener {
            //명시적 인텐트
            val intent: Intent = Intent(this, MainActivity5::class.java)
            intent.putExtra("x", 40)
            intent.putExtra("y", 20)
            intent.putExtra("operator", "-")
            startActivityForResult(intent, 10)
        }

        val activityResultLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.data?.getIntExtra("requestCode", 0) == 50) {
                    Toast.makeText(
                        applicationContext,
                        "${it.data?.getIntExtra("sum", 0)}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        binding.btnCallActivity6.setOnClickListener {
            val intent = Intent(this,MainActivity6::class.java)
            intent.putExtra("x", 45)
            intent.putExtra("y", 23)
            intent.putExtra("operator", "+")
            intent.putExtra("requestCode",60)
            activityResultLauncher.launch(intent)
        }
        binding.btnCallActivity7.setOnClickListener {
            val intent = Intent(this,MainActivity6::class.java)
            intent.putExtra("x", 2)
            intent.putExtra("y", 12)
            intent.putExtra("operator", "*")
            intent.putExtra("requestCode",50)
            activityResultLauncher.launch(intent)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 20 && resultCode == RESULT_OK) {
            Toast.makeText(applicationContext, "${data?.getIntExtra("sum", 0)}", Toast.LENGTH_SHORT)
                .show()
        } else if (requestCode == 10 && resultCode == RESULT_OK) {
            Toast.makeText(applicationContext, "${data?.getIntExtra("sum", 0)}", Toast.LENGTH_SHORT)
                .show()
        }


    }
   }
