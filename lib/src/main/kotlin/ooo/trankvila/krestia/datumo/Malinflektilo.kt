package ooo.trankvila.krestia.datumo

class Malinflektilo {
   private var radiko: Node? = null

   fun troviFinaĵon(vorto: String): Pair<String, Inflekcio>? =
      troviPlejLonganFinaĵon(radiko, vorto, 0, 0, null)?.let { (longeco, ŝtupo) ->
         val restantaVorto = vorto.substring(vorto.length - longeco, vorto.length)
         restantaVorto to ŝtupo
      }

   fun aldoniFinaĵon(finaĵo: String, inflekcio: Inflekcio) {
      radiko = aldoni(finaĵo, inflekcio, radiko, 0)
   }

   private fun aldoni(finaĵo: String, inflekcio: Inflekcio, node: Node?, longeco: Int): Node {
      if (longeco == finaĵo.length) {
         return Node(inflekcio)
      }

      val placo = node ?: Node(null)
      val sekva = finaĵo[finaĵo.length - longeco - 1] - 'a'
      placo.sekvaj[sekva] = aldoni(finaĵo, inflekcio, placo.sekvaj[sekva], longeco + 1)
      return placo
   }

   private fun troviPlejLonganFinaĵon(
      node: Node?,
      vorto: String,
      longeco: Int,
      lastaLongeco: Int,
      lastaValida: Inflekcio?
   ): Pair<Int, Inflekcio>? {
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

   private inner class Node(val ŝtupo: Inflekcio?) {
      val sekvaj = Array<Node?>(R) { null }
   }

   companion object {
      private const val R = 26
   }
}