// write the BankAccount class here
data class BankAccount(val deposited: Long, val withdrawn: Long) {
    var balance: Long = 0

    init {
        balance = deposited - withdrawn
    }
}