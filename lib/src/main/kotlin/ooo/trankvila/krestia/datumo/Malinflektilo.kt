package ooo.trankvila.krestia.datumo

class Malinflektilo {
   private var radiko: Node? = null

   fun troviFinaĵon(vorto: String): Pair<String, InflekciaŜtupo>? =
      troviPlejLonganFinaĵon(radiko, vorto, 0, 0, null)?.let { (longeco, ŝtupo) ->
         val restantaVorto = vorto.substring(vorto.length - longeco, vorto.length)
         when (ŝtupo) {
            is MalinflektiloŜtupo.SimplaŜtupo -> Pair(
               restantaVorto,
               ŝtupo.ŝtupo
            )
            is MalinflektiloŜtupo.KalkulaŜtupo -> Pair(
               restantaVorto,
               ŝtupo.ŝtupo(restantaVorto)
            )
         }
      }

   fun aldoniFinaĵon(finaĵo: String, ŝtupo: InflekciaŜtupo) {
      radiko = aldoni(finaĵo, MalinflektiloŜtupo.SimplaŜtupo(ŝtupo), radiko, 0)
   }

   fun aldoniFinaĵon(finaĵo: String, ŝtupo: (String) -> InflekciaŜtupo) {
      radiko = aldoni(finaĵo, MalinflektiloŜtupo.KalkulaŜtupo(ŝtupo), radiko, 0)
   }

   private fun aldoni(finaĵo: String, ŝtupo: MalinflektiloŜtupo, node: Node?, longeco: Int): Node {
      if (longeco == finaĵo.length) {
         return Node(ŝtupo)
      }

      val placo = node ?: Node(null)
      val sekva = finaĵo[finaĵo.length - longeco - 1] - 'a'
      placo.sekvaj[sekva] = aldoni(finaĵo, ŝtupo, placo.sekvaj[sekva], longeco + 1)
      return placo
   }

   private fun troviPlejLonganFinaĵon(
      node: Node?,
      vorto: String,
      longeco: Int,
      lastaLongeco: Int,
      lastaValida: MalinflektiloŜtupo?
   ): Pair<Int, MalinflektiloŜtupo>? {
      if (node == null) {
         if (lastaValida == null) return null
         return lastaLongeco to lastaValida
      }

      val sekvaValida = node.ŝtupo
      val sekvaLongeco = if (sekvaValida != null) longeco else lastaLongeco

      return troviPlejLonganFinaĵon(
         node.sekvaj[vorto[vorto.length - longeco - 1] - 'a'],
         vorto,
         longeco + 1,
         sekvaLongeco,
         sekvaValida ?: lastaValida
      )
   }

   private inner class Node(val ŝtupo: MalinflektiloŜtupo?) {
      val sekvaj = Array<Node?>(R) { null }
   }

   companion object {
      private const val R = 26
   }
}

private sealed class MalinflektiloŜtupo {
   data class SimplaŜtupo(val ŝtupo: InflekciaŜtupo) : MalinflektiloŜtupo()
   data class KalkulaŜtupo(val ŝtupo: (String) -> InflekciaŜtupo) : MalinflektiloŜtupo()
}