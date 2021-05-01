package ooo.trankvila.krestia.testoj

import ooo.trankvila.krestia.datumo.InflekciaŜtupo
import ooo.trankvila.krestia.datumo.Inflekcio
import ooo.trankvila.krestia.datumo.Malinflektilo
import ooo.trankvila.krestia.datumo.Vorttipo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.fail

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MalinflektiloTestoj {
   private val malinflektilo = Malinflektilo()

   @BeforeAll
   fun prepari() {
      with(malinflektilo) {
         aldoniFinaĵon("nsa", InflekciaŜtupo(Vorttipo.NombrigeblaKlaso, Inflekcio.Havaĵo))
         aldoniFinaĵon("ga", InflekciaŜtupo(Vorttipo.NombrigeblaKlaso, Inflekcio.AtributivoEstiMalantaŭ))
      }
   }

   @Test
   fun bazaTesto() {
      run {
         val (finaĵo, ŝtupo) = malinflektilo.troviFinaĵon("kunataga") ?: fail("kunataga estas valida")
         assertEquals("ga", finaĵo)
         assertEquals(InflekciaŜtupo(Vorttipo.NombrigeblaKlaso, Inflekcio.AtributivoEstiMalantaŭ), ŝtupo)
      }

      run {
         val rezulto2 = malinflektilo.troviFinaĵon("kunatava")
         assertNull(rezulto2)
      }
   }
}