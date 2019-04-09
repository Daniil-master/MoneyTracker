package online.daniilk.moneytracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import online.daniilk.moneytracker.api.Api;
import online.daniilk.moneytracker.api.BalanceResult;

public class BalanceFragment extends Fragment {
    private static final String TAG = "BalanceFragment";

    private DiagramView diagram;
    private Api api;
    private App app;

    private TextView total;
    private TextView expense;
    private TextView income;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getActivity().getApplication();
        api = app.getApi();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_balance, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // иниициализация данных

        total = view.findViewById(R.id.total);
        expense = view.findViewById(R.id.expense);
        income = view.findViewById(R.id.income);
        diagram = view.findViewById(R.id.diagram);
        updateData();
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser && isResumed()) {
//            updateData();
//        }
//    }

    private void updateData() {

//        Call<BalanceResult> call = api.balance();
//        call.enqueue(new Callback<BalanceResult>() {
//            @Override
//            public void onResponse(Call<BalanceResult> call, Response<BalanceResult> response) {
//                BalanceResult result = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<BalanceResult> call, Throwable t) {
//
//            }
//        });


        Random random = new Random();
        BalanceResult result = new BalanceResult();
        result.expense = random.nextInt(50000);
        result.income = random.nextInt(50000);

        total.setText(getString(R.string.price, result.income - result.expense));
        expense.setText(getString(R.string.price, result.expense));
        income.setText(getString(R.string.price, result.income));
        diagram.update(result.income, result.expense);

    }
}
