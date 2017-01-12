package com.r3bl.todo_app.ui.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.brianegan.bansa.Subscription;
import com.r3bl.todo_app.container.App;
import com.r3bl.todo_app.todoapp.R;

/** the todolist ui that is bound to the state */
public class TodoFragment extends Fragment {
protected EditText     textInput;
protected RecyclerView recyclerView;
private   Subscription subscriber;

public TodoFragment() {
}

@Override
public View onCreateView(LayoutInflater inflater,
                         ViewGroup container,
                         Bundle savedInstanceState) {

  App ctx = (App) getActivity().getApplicationContext();

  View view = inflater.inflate(R.layout.todo_fragment, container, false);

  textInput = (EditText) view.findViewById(R.id.todo_text_input);
  recyclerView = (RecyclerView) view.findViewById(R.id.todo_recycler_view);

  _updateUI(ctx);

  _bindToReduxState(ctx);

  return view;

}

@Override
public void onDestroyView() {
  if (subscriber != null) subscriber.unsubscribe();
  App.log("TodoFragment", "onDestroyView: [RAN]");
  super.onDestroyView();
}

private void _bindToReduxState(App ctx) {
  subscriber = ctx.getReduxStore().subscribe(state -> {
    App.log("TodoFragment", "redux state changed");
    // update the UI w/ stuff in the state object
    _updateUI(ctx);
  });
  App.log("TodoFragment", "_bindToReduxState: [RAN]");
}

private void _updateUI(App ctx) {
  Toast.makeText(ctx, "update todo ui now", Toast.LENGTH_SHORT).show();
}

}// end class TodoFragment