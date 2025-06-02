// Класс персонажа аниме (например, самурай, ниндзя или магический герой)
data class AnimeHero(
    val name: String,
    val chakra: Int,  // мана/энергия/чакра
    val powerLevel: Int  // уровень силы
)

fun animeBattleDemo(): AnimeHero {

    // 1. Чистая функция — создаёт нового героя, не изменяя внешние данные
    fun createHero(name: String, chakra: Int, powerLevel: Int): AnimeHero {
        return AnimeHero(name, chakra, powerLevel)
    }

    // 2. Неизменяемость — "тренировка" увеличивает уровень силы, но старый герой не меняется
    fun trainHero(hero: AnimeHero, newChakra: Int): AnimeHero {
        return hero.copy(chakra = newChakra)
    }

    // 3. Функция высшего порядка — принимает "технику" (функцию усиления)
    fun useTechnique(
        hero: AnimeHero,
        technique: (AnimeHero) -> AnimeHero
    ): AnimeHero {
        return technique(hero)
    }

    // 4. Каррирование — "зачарование" героя (последовательное усиление)
    fun enchantHero(name: String): (Int) -> (Int) -> AnimeHero {
        return { chakra -> { power -> AnimeHero(name, chakra, power) } }
    }

    // 5. Рекурсия — "атака комбо" (многократное усиление)
    fun comboAttack(hero: AnimeHero, hits: Int): AnimeHero {
        if (hits <= 0) return hero
        val poweredUpHero = hero.copy(powerLevel = hero.powerLevel + 15)
        return comboAttack(poweredUpHero, hits - 1)
    }

    val baseHero = createHero("Naruto", 100, 50)
    val trainedHero = trainHero(baseHero, 150)
    val specialMoveHero = useTechnique(trainedHero) { h -> h.copy(powerLevel = h.powerLevel + 20) }
    val enchantedHero = enchantHero("Sasuke")(120)(70)
    val finalHero = comboAttack(specialMoveHero, 3)

    return finalHero
}

fun main() {
    val hero = animeBattleDemo()
    println(hero)
}