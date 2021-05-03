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
   Imperativo,
   Argumento1,
   Argumento2,
   Argumento3,
   Ekzistado,
   Hortativo,
   Translativo,
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
data class MalinflektitaVorto(val bazaVorto: String, val vorttipo: Vorttipo, val ŝtupoj: List<MalinflekaŜtupo>)