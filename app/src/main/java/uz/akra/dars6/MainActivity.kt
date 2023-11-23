package uz.akra.dars6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.akra.dars6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var end = false
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btn0.setOnClickListener { clickNumber("0") }
            btn1.setOnClickListener { clickNumber("1") }
            btn2.setOnClickListener { clickNumber("2") }
            btn3.setOnClickListener { clickNumber("3") }
            btn4.setOnClickListener { clickNumber("4") }
            btn5.setOnClickListener { clickNumber("5") }
            btn6.setOnClickListener { clickNumber("6") }
            btn7.setOnClickListener { clickNumber("7") }
            btn8.setOnClickListener { clickNumber("8") }
            btn9.setOnClickListener { clickNumber("9") }
            btnPlus.setOnClickListener { clickAmal(0) }
            btnMinus.setOnClickListener { clickAmal(1) }
            btnMultiply.setOnClickListener { clickAmal(2) }
            btnBolish.setOnClickListener { clickAmal(3) }
            btnFoiz.setOnClickListener { clickAmal(4) }
            btnTeng.setOnClickListener {
                if (!end) hisoblash()
            }
            btnAc.setOnClickListener { tvDisplay.text = "0" }
            btnClearone.setOnClickListener {
                if (tvDisplay.text.length == 1 || tvDisplay.text == "-") {
                    tvDisplay.text = "0"
                } else if (!tvDisplay.text.contains("")) {
                    tvDisplay.text = tvDisplay.text.substring(0, tvDisplay.text.length - 1)
                } else if (tvDisplay.text.length > 1 || tvDisplay.text == "+" || tvDisplay.text == "-") {
                    tvDisplay.text = tvDisplay.text.substring(0, tvDisplay.text.length - 1)
                }
            }

            btnNuqta.setOnClickListener {
                var n = tvDisplay.text.toString()
                var amalIndex = -1
                for (i in n.indices) {
                    if (n[i] == '+' || n[i] == '-' || n[i] == '×' || n[i] == '÷' || n[i] == '%') {
                        amalIndex = i
                    }
                }
                if (amalIndex == -1) {
                    if (!tvDisplay.text.toString().contains('.')) {
                        tvDisplay.text = "${tvDisplay.text}."
                    }
//                    else {
//                        var ekranLength = tvDisplay.text.length
//                        var matn = tvDisplay.text.substring(amalIndex, ekranLength)
//                        if (!matn.contains('.')) {
//                            tvDisplay.text = "${tvDisplay.text}."
//                        }
//                    }
                }
            }


        }
    }


    var raqamBosildimi = false
    fun clickNumber(number: String) {
        var ekran = binding.tvDisplay.text.toString()

            if (ekran != "0" && !end) {
                ekran += number

            } else  {
                ekran = number
                end = false
            }
            binding.tvDisplay.text = ekran
            raqamBosildimi = true
        }


    fun clickAmal(amal: Int) {
        var ekran = binding.tvDisplay.text.toString()
        if (raqamBosildimi) {
            when (amal) {
                0 -> {
                    ekran += "+"
                }

                1 -> {
                    ekran += "-"
                }

                2 -> {
                    ekran += "×"
                }

                3 -> {
                    ekran += "÷"
                }

                4 -> {
                    ekran += "%"
                }

            }
        } else {
            when (amal) {
                0 -> {
                    ekran = ekran.substring(0, ekran.length - 1) + "+"
                }

                1 -> {
                    ekran = ekran.substring(0, ekran.length - 1) + "-"
                }

                2 -> {
                    ekran = ekran.substring(0, ekran.length - 1) + "×"
                }

                3 -> {
                    ekran = ekran.substring(0, ekran.length - 1) + "÷"
                }
            }
        }
        raqamBosildimi = false
        binding.tvDisplay.text = ekran
    }

    fun hisoblash(){
        var sonlar = ArrayList<Double>()
        var amallar = ArrayList<Int>()

        val misol = binding.tvDisplay.text.toString()

        var index1 = 0
        for (i in misol.indices) {
            if (amallar.isEmpty()) {
                when (misol[i]) {
                    '+' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(0)
                        index1 = i
                    }
                    '-' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(1)
                        index1 = i
                    }
                    '*' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(2)
                        index1 = i
                    }
                    '/' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(3)
                        index1 = i
                    }
                }
            }else{
                when (misol[i]) {
                    '+' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(0)
                        index1 = i
                    }
                    '-' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(1)
                        index1 = i
                    }
                    '*' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(2)
                        index1 = i
                    }
                    '/' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(3)
                        index1 = i
                    }
                }
            }
        }

        sonlar.add(misol.subSequence(index1+1, misol.length).toString().toDouble())

        var count = 0
        var natija = sonlar.first()
        while (count < amallar.size) {

            when (amallar[count]) {
                0 -> {
                    natija += sonlar[count + 1]
                }
                1 -> {
                    natija -= sonlar[count + 1]
                }
                2 -> {
                    natija *= sonlar[count + 1]
                }
                3 -> {
                    natija /= sonlar[count + 1]
                }
            }

            count++
        }

        val intNatija:Int = natija.toInt()
        if (natija / intNatija == 1.0){

            binding.tvDisplay.text = "$intNatija"
        }else {
            binding.tvDisplay.text = "$natija"
        }
        end = true
    }
}