package ooo.trankvila.krestia.testoj

import ooo.trankvila.krestia.datumo.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.fail

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MalinflektiloTestoj {
   @Test
   fun bazaTesto() {
      run {
         val (finaĵo, ŝtupo) = Malinflektado.troviFinaĵon("kunataga") ?: fail("kunataga estas valida")
         assertEquals("ga", finaĵo)
         assertEquals(
            InflekciaŜtupo(Vorttipo.NenombrigeblaKlaso, Inflekcio.Difinito, Inflekcio.AtributivoEstiMalantaŭ),
            ŝtupo
         )
      }

      run {
         val rezulto2 = Malinflektado.troviFinaĵon("kunatava")
         assertNull(rezulto2)
      }

      run {
         val (finaĵo, ŝtupo) = Malinflektado.troviFinaĵon("kunatavra")!!
         assertEquals("vra", finaĵo)
         assertEquals(
            InflekciaŜtupo(Vorttipo.NenombrigeblaKlaso, Inflekcio.Difinito, Inflekcio.SpecifaĜerundo),
            ŝtupo
         )
      }

      run {
         val (finaĵo, ŝtupo) = Malinflektado.troviFinaĵon("kunataavra")!!
         assertEquals("vraa", finaĵo)
         assertEquals(
            InflekciaŜtupo(Vorttipo.NenombrigeblaKlaso, Inflekcio.Difinito, Inflekcio.Ĝerundo),
            ŝtupo
         )
      }
   }

   @Test
   fun multaŜtupaTesto() {
      run {
         val vorto = Malinflektado.tuteMalinflekti("kunaarega")
         assertEquals("kuna", vorto.bazaVorto)
         assertEquals(Vorttipo.NenombrigeblaKlaso, vorto.vorttipo)
      }
   }
}