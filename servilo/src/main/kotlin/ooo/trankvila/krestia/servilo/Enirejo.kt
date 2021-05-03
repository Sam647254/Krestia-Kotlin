package ooo.trankvila.krestia.servilo

import ooo.trankvila.krestia.datumo.Malinflektado
import ooo.trankvila.krestia.datumo.MalinflektitaVorto
import ooo.trankvila.krestia.test
import kotlin.system.measureTimeMillis

fun main() {
   while (true) {
      val eniro = readLine() ?: return
      lateinit var vorto: MalinflektitaVorto
      val daŭro = measureTimeMillis {
         vorto = Malinflektado.tuteMalinflekti(eniro)
      }
      println(vorto.bazaVorto)
      println(vorto.ŝtupoj)
      println(daŭro)
   }
}