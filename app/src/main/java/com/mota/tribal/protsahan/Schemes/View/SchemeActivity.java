package com.mota.tribal.protsahan.Schemes.View;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.mota.tribal.protsahan.R;
import com.mota.tribal.protsahan.Schemes.Model.Data.InnerData;
import com.mota.tribal.protsahan.Schemes.Model.Data.SchemeInfo;
import com.mota.tribal.protsahan.Schemes.Model.MockScheme;
import com.mota.tribal.protsahan.Schemes.Presenter.SchemePresenter;
import com.mota.tribal.protsahan.Schemes.Presenter.SchemePresenterImpl;
import com.mota.tribal.protsahan.Schemes.View.outer.OuterAdapter;
import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;

import java.util.List;


public class SchemeActivity extends AppCompatActivity implements SchemeView {

    private SchemePresenter presenter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);
        progressBar = findViewById(R.id.progress_bar);

        presenter = new SchemePresenterImpl(new MockScheme(), SchemeActivity.this, SchemeActivity.this);
        presenter.getResponse();

    }



   /* @Override
    public void onFakerReady(final Faker faker) {
        Single.create(new SingleOnSubscribe<List<List<SchemeInfo>>>() {
            @Override
            public void subscribe(SingleEmitter<List<List<SchemeInfo>>> e) throws Exception {
                final List<List<SchemeInfo>> outerData = new ArrayList<>();
                for (int i = 0; i < OUTER_COUNT && !e.isDisposed(); i++) {
                    final List<SchemeInfo> innerData = new ArrayList<>();
                    for (int j = 0; j < INNER_COUNT && !e.isDisposed(); j++) {
                        innerData.add(createInnerData(faker));
                    }
                    outerData.add(innerData);
                }

                if (!e.isDisposed()) {
                    e.onSuccess(outerData);
                }
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<List<SchemeInfo>>>() {
            @Override
            public void accept(List<List<SchemeInfo>> outerData) throws Exception {
                initRecyclerView(outerData);
            }
        });
    }*/

    private void initRecyclerView(List<List<InnerData>> data) {
        findViewById(R.id.progressBar).setVisibility(View.GONE);

        final TailRecyclerView rv = findViewById(R.id.recycler_view);
        ((TailLayoutManager) rv.getLayoutManager()).setPageTransformer(new HeaderTransformer());
        rv.setAdapter(new OuterAdapter(data));

        new TailSnapHelper().attachToRecyclerView(rv);
    }

    @Override
    public void showProgressBar(boolean b) {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.login), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void getSchemes(SchemeInfo data) {
        final List<List<InnerData>> outerData = data.innerData;
        initRecyclerView(outerData);
    }

  /*  @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnInnerItemClick(InnerItem item) {
        final SchemeInfo itemData = item.getItemData();
        if (itemData == null) {
            return;
        }

        DetailsActivity.start(this,
                item.getItemData().name, item.mAddress.getText().toString(),
                item.getItemData().avatarUrl, item.itemView, item.mAvatarBorder);
    }*/

}
