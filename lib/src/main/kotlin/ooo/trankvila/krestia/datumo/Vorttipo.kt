package ooo.trankvila.krestia.datumo

enum class Inflekcio {
   Difinito,
   Havaĵo,
   Fokuso,
   Progresivo,
   Perfekto,
   Estonteco,
   Desiderativo,
   PredikativoEsti,
   AtributivoEstiAntaŭ,
   AtributivoEstiMalantaŭ,
   Havado,
   Havado0,
   Imperativo,
   Argumento1,
   Argumento2,
   Argumento3,
   Ekzistado,
   Hortativo,
   Translativo,
   Translativo0,
   Ĝerundo,
   SpecifaĜerundo,
   PartaUjo1,
   PartaUjo2,
   PartaUjo3,
   Neinflektita,
   Sola,
   Reflekcio,
   UnueUjo2,
   UnueUjo3,
   Optativo,
   Kvalito,
   Hipoteza,
   Apartigita,
   FremdaVorto,
   Cifero,
   Predikato
}

enum class Vorttipo {
   NombrigeblaKlaso,
   NenombrigeblaKlaso,
   Rekordo,
   MalantaŭNombrigeblaEco,
   AntaŭNombrigeblaEco,
   MalantaŭNenombrigeblaEco,
   AntaŭNenombrigeblaEco,
   Verbo0,
   Verbo1,
   Verbo2,
   Verbo3,
   Verbo12,
   Verbo13,
   Verbo23,
   Verbo123,
   Lokokupilo,
   MalantaŭModifanto,
   AntaŭModifanto,
   Makro,
   FremdaVorto,
   Cifero
}

data class InflekciaŜtupo(val bazo: Vorttipo, val bazaInflekcio: Inflekcio, val inflekcio: Inflekcio)
data class MalinflekaŜtupo(val finaĵo: String, val inflekcio: Inflekcio)
data class MalinflektitaVorto(val bazaVorto: String, val vorttipo: Vorttipo, val legitajŜtupoj: List<MalinflekaŜtupo>) {
   val ŝtupoj = sequence {
      var lastaŜtupo: Inflekcio? = null
      legitajŜtupoj.forEach { ŝtupo ->
         if (ŝtupo.inflekcio != Inflekcio.PredikativoEsti) {
            if (lastaŜtupo == Inflekcio.PredikativoEsti) {
               when (ŝtupo.inflekcio) {
                  Inflekcio.SpecifaĜerundo -> yield(Inflekcio.Ĝerundo)
                  Inflekcio.Kvalito -> yield(Inflekcio.Kvalito)
                  else -> {
                     yield(lastaŜtupo!!)
                     yield(ŝtupo.inflekcio)
                  }
               }
            } else {
               yield(ŝtupo.inflekcio)
            }
         }

         lastaŜtupo = ŝtupo.inflekcio
      }

      if (lastaŜtupo == Inflekcio.PredikativoEsti) {
         yield(lastaŜtupo!!)
      }
   }.toList()

   val ĉuValida by lazy {
      var vorttipo: Vorttipo? = this.vorttipo
      ŝtupoj.forEach { inflekcio ->
         if (vorttipo == null) return@lazy false
         vorttipo = validajInflekcioj[vorttipo]?.get(inflekcio)
      }

      true
   }
}

val kielDifinito = setOf(
   Inflekcio.Difinito,
   Inflekcio.Ĝerundo,
   Inflekcio.SpecifaĜerundo,
   Inflekcio.Argumento1,
   Inflekcio.Argumento2,
   Inflekcio.Argumento3,
   Inflekcio.Havaĵo,
   Inflekcio.FremdaVorto,
   Inflekcio.Cifero,
   Inflekcio.Apartigita,
)

val substantivajInflekcioj = mapOf(
   Inflekcio.PredikativoEsti to null,
   Inflekcio.Fokuso to null,
   Inflekcio.AtributivoEstiAntaŭ to null,
   Inflekcio.AtributivoEstiMalantaŭ to null,
   Inflekcio.Sola to null,
   Inflekcio.Havado to Vorttipo.Verbo1,
   Inflekcio.Havado0 to Vorttipo.Verbo0,
   Inflekcio.Ekzistado to Vorttipo.Verbo0,
   Inflekcio.Translativo to Vorttipo.Verbo1,
   Inflekcio.Translativo0 to Vorttipo.Verbo0,
   Inflekcio.SpecifaĜerundo to Vorttipo.NenombrigeblaKlaso,
   Inflekcio.Ĝerundo to Vorttipo.NenombrigeblaKlaso,
   Inflekcio.Kvalito to Vorttipo.NenombrigeblaKlaso
)

val ecajInflekcioj = substantivajInflekcioj + mapOf(
   Inflekcio.Apartigita to Vorttipo.NombrigeblaKlaso
)

val validajInflekcioj = mapOf(
   Vorttipo.NombrigeblaKlaso to substantivajInflekcioj,
   Vorttipo.NenombrigeblaKlaso to substantivajInflekcioj,
   Vorttipo.MalantaŭNombrigeblaEco to ecajInflekcioj
)