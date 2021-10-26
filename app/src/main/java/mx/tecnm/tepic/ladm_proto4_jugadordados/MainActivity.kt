package mx.tecnm.tepic.ladm_proto4_jugadordados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jugador1 = HiloJugador1(this)
        val jugador2 = HiloJugador2(this)


        button.setOnClickListener {
            try {
                jugador1.start()

                jugador2.start()
            } catch (io: Exception){
                Toast.makeText(this, "ERROR HILO YA SE EJECUTO START", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}

class HiloJugador1(p:MainActivity) : Thread(){
    var total = 0
    var tiro = 0
    var turno = true
    var tiro1=0
    var tiro2=0
    val puntero = p
    var registro1 = ""
    var registro2 = ""

    override fun run() {
        super.run()
        while(turno && tiro < 2){
            puntero.runOnUiThread {
                tiro1 = tirarDado()

                registro1= "TIRO 1: (${tiro1}) "
                if(tiro > 1){
                    tiro2 = tirarDado()
                    total = tiro1+tiro2
                    registro2= " TIRO 2: (${tiro2}) Total: ${total}"
                }
                puntero.txtRegistro1.text = "REGISTRO: "+registro1+registro2
            }
            tiro += 1
        }
        sleep(200)
    }

    fun tirarDado() : Int{
        return ((Math.random()*6)+1).toInt()
    }

    fun turno(){
        turno = !turno
    }
}

class HiloJugador2(p:MainActivity) : Thread(){
    var total = 0
    var tiro = 0
    var turno = true
    var tiro1=0
    var tiro2=0
    val puntero = p
    var registro1 = ""
    var registro2 = ""

    override fun run() {
        super.run()
        while(turno && tiro < 2){
            puntero.runOnUiThread {
                tiro1 = tirarDado()

                registro1= "TIRO 1: (${tiro1}) "
                if(tiro > 1){
                    tiro2 = tirarDado()
                    total = tiro1+tiro2
                    registro2= " TIRO 2: (${tiro2}) Total: ${total}"
                }
                puntero.txtRegistro2.text =  "REGISTRO: "+registro1+registro2
            }
            tiro += 1
        }
        sleep(200)
    }

    fun tirarDado() : Int{
        return ((Math.random()*6)+1).toInt()
    }

    fun turno(){
        turno = !turno
    }
}