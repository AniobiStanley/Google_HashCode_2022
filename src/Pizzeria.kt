import java.io.File
import java.util.*

class Pizzeria {
}

fun main(){
    val likedIngredients = mutableListOf<Array<String>>()
    val dislikedIngredients = mutableListOf<Array<String>>()
    val scan = Scanner(System.`in`)
    val numberOfCustomers = scan.nextLine().trim().toInt()

    for (i in 1..(2*numberOfCustomers)){
        if (i%2 != 0){
            val numberOfIngredients = scan.nextInt()
            val ingredients = scan.nextLine().trim().split(" ").map{it.trim()}.toTypedArray()
            likedIngredients.add(ingredients)
        }
        else if (i%2 == 0){
            val numberOfIngredients = scan.nextInt()
            if(numberOfIngredients != 0){
                val ingredients = scan.nextLine().trim().split(" ").map{it.trim()}.toTypedArray()
                dislikedIngredients.add(ingredients)
            }
        }
    }

    val ingredient = likedIngredients.map { it }.groupingBy { it }.eachCount()
    val invIngradients = ingredient.entries.associate { (key, value) -> value to key }
    invIngradients.toSortedMap(reverseOrder())
    val pizzeriaIngredient = mutableListOf<String>()
    var maxCount = 0
    var finalIngradient = mutableListOf<String>()
    for(i in invIngradients.values){
        pizzeriaIngredient.add(i[0])
        var count = 0
        for (n in likedIngredients){
            var availability = true
            for (j in n){
                if (j !in pizzeriaIngredient) availability = false
            }
            if (availability) count++
        }
        if(count > maxCount){
            maxCount = count
            finalIngradient = pizzeriaIngredient
        }

    }
    val numberOfIngredient = finalIngradient.count()
    val fileName = "src/elaborate.txt"
    val myFile = File(fileName)

    myFile.printWriter().use { out ->

        out.println("${numberOfIngredient} ${finalIngradient.joinToString()}")
    }
}

