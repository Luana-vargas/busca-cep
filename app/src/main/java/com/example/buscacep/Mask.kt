package com.example.buscacep

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class Mask{

    companion object {

        private fun replaceChars(txt: String): String {
            return txt.replace(Regex("[\\D]"), "")
        }


        fun custom(mask: String, edit: EditText): TextWatcher {
            val textWatcher: TextWatcher = object: TextWatcher {

                var isUpdating: Boolean = false
                var oldString: String = ""

                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val str = replaceChars(s.toString())
                    var formated = ""

                    if (count == 0) // is deleting
                        isUpdating = true

                    if (isUpdating){
                        oldString = str
                        isUpdating = false
                        return
                    }

                    var i = 0

                    for (m: Char in mask.toCharArray()){
                        if (m != '#' && str.length > oldString.length){
                            formated += m
                            continue
                        }
                        try {
                            formated += str.get(i)
                        }catch (e: Exception){
                            break
                        }
                        i++
                    }

                    isUpdating = true
                    edit.setText(formated)
                    edit.setSelection(formated.length)

                }

            }
            return textWatcher
        }
    }

}