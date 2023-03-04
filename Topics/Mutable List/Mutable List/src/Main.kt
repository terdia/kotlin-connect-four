fun names(namesList: List<String>): List<String> {
    val nameCount = mutableListOf<String>()

    val seen = mutableListOf<String>()
    for (name in namesList) {

        if (seen.contains(name)) {
            continue
        }
        seen.add(name)
        nameCount.add("The name $name is repeated ${namesList.count { it == name }} times")
    }

    return nameCount
}