package com.example.thongtincanhan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var buttonCheck: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Khởi tạo các thành phần UI
        editTextName = findViewById(R.id.editTextText)
        editTextAge = findViewById(R.id.editTextNumber)
        buttonCheck = findViewById(R.id.button)

        // Thiết lập sự kiện click cho nút kiểm tra
        buttonCheck.setOnClickListener {
            checkUserInfo()
        }
    }

    private fun checkUserInfo() {
        val name = editTextName.text.toString().trim()
        val ageText = editTextAge.text.toString().trim()

        // Kiểm tra dữ liệu nhập vào
        if (name.isEmpty() || ageText.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val age = ageText.toInt()

            // Xác định nhóm tuổi
            val ageGroup = when {
                age > 65 -> "Người già (>65)"
                age in 6..65 -> "Người lớn (6-65)"
                age in 2..6 -> "Trẻ em (2-6)"
                age < 2 -> "Em bé (<2)"
                else -> "Không xác định"
            }

            // Hiển thị kết quả
            showResultDialog(name, age, ageGroup)
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Tuổi phải là số", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showResultDialog(name: String, age: Int, ageGroup: String) {
        val message = "Họ và tên: $name\nTuổi: $age\nNhóm tuổi: $ageGroup"

        AlertDialog.Builder(this)
            .setTitle("Thông tin người dùng")
            .setMessage(message)
            .setPositiveButton("Đóng") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}