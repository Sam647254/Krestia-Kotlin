package ooo.trankvila.krestia.datumo

class Malinflektilo {
   private var radiko: Node? = null

   fun troviFinaĵon(vorto: String): Pair<String, InflekciaŜtupo>? =
      troviPlejLonganFinaĵon(radiko, vorto, 0, 0, null)?.let { (longeco, ŝtupo) ->
         Pair(vorto.substring(vorto.length - longeco, vorto.length), ŝtupo)
      }

   fun aldoniFinaĵon(finaĵo: String, ŝtupo: InflekciaŜtupo) {
      radiko = aldoni(finaĵo, ŝtupo, radiko, 0)
   }

   private fun aldoni(finaĵo: String, ŝtupo: InflekciaŜtupo, node: Node?, longeco: Int): Node {
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
      lastaValida: InflekciaŜtupo?
   ): Pair<Int, InflekciaŜtupo>? {
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

   private inner class Node(val ŝtupo: InflekciaŜtupo?) {
      val sekvaj = Array<Node?>(R) { null }
   }

   companion object {
      private const val R = 26
   }
}