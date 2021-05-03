package ooo.trankvila.krestia.datumo

object Malinflektado {
   private val malinflektilo: Malinflektilo = Malinflektilo().apply {
      listOf(
         "nsa" to Inflekcio.Havaĵo,
         "le" to Inflekcio.Fokuso,
         "ga" to Inflekcio.AtributivoEstiMalantaŭ,
         "va" to Inflekcio.AtributivoEstiAntaŭ,
         "ra" to Inflekcio.Sola,
         "rem" to Inflekcio.Havado,
         "res" to Inflekcio.Havado,
         "rim" to Inflekcio.Ekzistado,
         "vra" to Inflekcio.SpecifaĜerundo,
      ).forEach { (finaĵo, inflekcio) ->
         aldoniFinaĵon(finaĵo, InflekciaŜtupo(Vorttipo.NombrigeblaKlaso, Inflekcio.Difinito, inflekcio))
         aldoniFinaĵon(finaĵo, InflekciaŜtupo(Vorttipo.NenombrigeblaKlaso, Inflekcio.Difinito, inflekcio))
         listOf(
            Vorttipo.AntaŭNenombrigeblaEco,
            Vorttipo.AntaŭNombrigeblaEco,
            Vorttipo.MalantaŭNenombrigeblaEco,
            Vorttipo.MalantaŭNombrigeblaEco
         ).forEach { vorttipo ->
            aldoniFinaĵon(finaĵo, InflekciaŜtupo(vorttipo, Inflekcio.Difinito, inflekcio))
         }
      }

      listOf(
         "lam" to Inflekcio.Translativo,
         "las" to Inflekcio.Translativo,
         "vra" to Inflekcio.Ĝerundo,
         "re" to Inflekcio.Kvalito
      ).forEach { (finaĵo, inflekcio) ->
         aldoniFinaĵon(finaĵo, InflekciaŜtupo(Vorttipo.NombrigeblaKlaso, Inflekcio.PredikativoEsti, inflekcio))
         aldoniFinaĵon(finaĵo, InflekciaŜtupo(Vorttipo.NenombrigeblaKlaso, Inflekcio.PredikativoEsti, inflekcio))
         listOf(
            Vorttipo.AntaŭNenombrigeblaEco,
            Vorttipo.AntaŭNombrigeblaEco,
            Vorttipo.MalantaŭNenombrigeblaEco,
            Vorttipo.MalantaŭNombrigeblaEco
         ).forEach { vorttipo ->
            aldoniFinaĵon(finaĵo, InflekciaŜtupo(vorttipo, Inflekcio.PredikativoEsti, inflekcio))
         }
      }

      listOf(
         Vorttipo.AntaŭNenombrigeblaEco,
         Vorttipo.AntaŭNombrigeblaEco,
         Vorttipo.MalantaŭNenombrigeblaEco,
         Vorttipo.MalantaŭNombrigeblaEco
      ).forEach { vorttipo ->
         aldoniFinaĵon("la", InflekciaŜtupo(vorttipo, Inflekcio.Difinito, Inflekcio.Apartigita))
      }
   }

   fun tuteMalinflekti(vorto: String): MalinflektitaVorto {
      val ŝtupoj = mutableListOf<MalinflekaŜtupo>()
      var restantaVorto = vorto
      while (true) {
         val sekvaŜtupo = troviFinaĵon(restantaVorto)
         if (sekvaŜtupo == null) {
            val (bazaVorto, vorttipo) = troviTiponDeBazaVorto(restantaVorto) ?: throw Exception("Invalid word: $vorto")
            return MalinflektitaVorto(bazaVorto, vorttipo, ŝtupoj)
         } else {
            val (finaĵo, ŝtupo) = sekvaŜtupo
            ŝtupoj.add(MalinflekaŜtupo(finaĵo, ŝtupo.inflekcio))
            restantaVorto = restantaVorto.substring(0, restantaVorto.length - finaĵo.length)
         }
      }
   }

   fun troviFinaĵon(vorto: String) = malinflektilo.troviFinaĵon(vorto)

   fun troviTiponDeBazaVorto(vorto: String): Pair<String, Vorttipo>? {
      predikativaEstiFinaĵoj.forEach { (finaĵo, vorttipo) ->
         if (vorto.endsWith(finaĵo)) {
            val difinito = if (vorto.endsWith("aa")) {
               vorto.substring(0, vorto.length - 2) + "a"
            } else {
               vorto.substring(0, vorto.length - 1) + if (vorto.endsWith('o')) 'e' else 'i'
            }

            return difinito to vorttipo
         }
      }

      difinitoFinaĵoj.forEach { (finaĵo, vorttipo) ->
         if (vorto.endsWith(finaĵo)) {
            return vorto to vorttipo
         }
      }

      return null
   }

   private val predikativaEstiFinaĵoj = run {
      val finaĵoj = mutableMapOf<String, Vorttipo>()
      listOf("paa", "po", "pu", "taa", "to", "tu", "kaa", "ko", "ku").forEach { finaĵo ->
         finaĵoj[finaĵo] = Vorttipo.NombrigeblaKlaso
      }
      listOf("maa", "mo", "mu", "naa", "no", "nu").forEach { finaĵo ->
         finaĵoj[finaĵo] = Vorttipo.NenombrigeblaKlaso
      }
      finaĵoj.toMap()
   }
   private val difinitoFinaĵoj = run {
      val finaĵoj = mutableMapOf<String, Vorttipo>()
      listOf("pa", "pe", "pi", "ta", "te", "ti", "ka", "ke", "ki").forEach { finaĵo ->
         finaĵoj[finaĵo] = Vorttipo.NombrigeblaKlaso
      }
      listOf("ma", "me", "mi", "na", "ne", "ni").forEach { finaĵo ->
         finaĵoj[finaĵo] = Vorttipo.NenombrigeblaKlaso
      }
      finaĵoj.toMap()
   }
}