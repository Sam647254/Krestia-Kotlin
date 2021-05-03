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
   fun multaŜtupaTesto() {
      run {
         val vorto = Malinflektado.tuteMalinflekti("kunaarega")
         assertEquals("kuna", vorto.bazaVorto)
         assertEquals(Vorttipo.NenombrigeblaKlaso, vorto.vorttipo)
         assertEquals(listOf(Inflekcio.Kvalito, Inflekcio.AtributivoEstiMalantaŭ), vorto.ŝtupoj)
      }
   }
}