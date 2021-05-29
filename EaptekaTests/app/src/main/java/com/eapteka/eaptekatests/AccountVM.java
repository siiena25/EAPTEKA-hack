package com.eapteka.eaptekatests;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountVM extends ViewModel {
    MutableLiveData<AccountData> accountData = new MutableLiveData<>();

    public void updateHappyLevel(float delta) {
        if (accountData.getValue().happyLevel + delta <= 1)
            accountData.setValue(new AccountData(accountData.getValue().happyLevel + delta,
                    accountData.getValue().coins));
    }

    public void updateСoins(int delta) {
        accountData.setValue(new AccountData(accountData.getValue().happyLevel,
                accountData.getValue().coins + delta));
    }

    public AccountData getAccountData() {
        return accountData.getValue();
    }
}
