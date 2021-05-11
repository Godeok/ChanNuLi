package kr.co.healthcare.healthInfo.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.diseaseInfo.contents.DiseaseInfoAdapter;


public class Diet extends Fragment {
    private  ArrayList<DietData> arrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>();
        getDietData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.diet_RV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        DietRecyclerviewAdapter adapter = new DietRecyclerviewAdapter(arrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    void getDietData(){
        arrayList.add(new DietData(
                "영양관리",
                "1. 규칙적으로 적은 양을 자주 먹는다.\n" +
                        "- 수분: 나이가 들수록 수분을 적게 마셔서 변비나 탈수현상이 생기기 쉬우므로 하루 8잔의 물을 섭취한다.\n" +
                        "- 곡류 및 전분류: 곡류와 전분류는 생활에서 힘을 내게 한다. 이중 식이섬유소는 변비나 암을 예방하고, 혈당과 혈중 콜레스테롤을 낮추어 준다.\n" +
                        "- 식이섬유소: 흰밥, 흰빵보다 도정이 덜 된 잡곡밥을 먹는다. 채소, 과일, 해조류를 충분히 먹는다.\n" +
                        "- 채소 및 과일류: 채소와 과일에는 비타민과 무기질이 많아서 몸의 정상적인 활동에 도움을 준다.\n" +
                        "- 고기, 생선, 달걀,콩류: 고기, 생선, 달걀과 콩류는 단백질 식품으로 우리 몸을 만들고 유지하는데 필요하다. 노인들은 질이 좋은 단백질을 충분히 섭취해야 한다.\n" +
                        "- 우유 및 유제품: 우유와 유제품에 많은 칼슘은 뼈나 치아를 만드는데 필요하다. 나이가 들수록 뼈에 있는 칼슘이 빠져나가 골다공증에 걸리기 쉽다. 하루 700mg 정도의 칼슘을 섭취해야 한다.\n" +
                        "- 유지·견과 및 당류: 주로 단순당, 지방으로 되어 있어 열량만 제공한다. 당류는 설탕, 꿀, 사탕 등으로 많이 먹으면 식욕이 줄고, 비만이 되기 쉬우므로 조심해야 한다. 지방은 열량을 많이 내고 음식 맛을 좋게 하며 포만감을 준다. 동물성 기름과 콜레스테롤을 적게 먹으려면 기름기는 제거하고, 살짝 삶은 후 조리한다. 삼겹살, 닭껍질 등 기름기가 많은 부위는 자주 먹지 않는다. 육류보다 생선을 즐긴다. 고기요리에는 채소를 곁들이거나 찜, 구이를 이용한다.\n" +
                        "2. 식사 때마다 영양소 밀도가 높은 음식을 포함시킨다. 노인은 성인과 비교해 에너지 필요량은 적으나 다른 영양소 권장량은 큰 차이가 없으며, 한꺼번에 많은 양을 먹지 못하므로 영양밀도가 높은 식단을 짜야 한다. 육류·우유·치즈·달걀·두부 등의 섭취가 중요하다.\n" +
                        "3. 노인의 기호, 치아상태, 소화능력을 고려하여 식품의 선택과 조리방법에 세심한 배려가 필요하다. 노인들은 부드럽고 달고 따뜻한 음식을 좋아하고, 맵고 짜고 신 것을 싫어한다. 생선과 육류를 좋아하고 채소 및 과일을 별로 좋아하지 않는 경향이다.\n" +
                        "4. 색,풍미,형태가 다양한 음식을 차리고, 밝고 환한 곳에서 식사를 하여 식욕을 돋군다. 식사전에 간단한 몸 동작을 하거나 걸러보는 것도 식욕을 돋굴 수 있다.\n" +
                        "5. 노인들은 단맛에 대한 역치의 증가, 또는 외로움을 달래기 위해 단맛이 강한 연질음식이나 음료를 좋아하게 된다. 케이크나 과자류·사탕·초콜렛·콜라·사이다 등 'empty calorie'식품이므로 과잉 섭취하지 않도록 한다.\n" +
                        "6. 변비와 만성 질환의 유병률이 높으므로 가능한 신선한 과일, 채소, 해조류, 두류를 많이 섭취하도록 한다. 신선한 채소와 과일은 노인에게 부족 되기 쉬운 비타민 A를 비롯한 여러 비타민과 무기질, 식이섬유질의 좋은 급원이다.\n" +
                        "7. 칼슘 보충을 위해 우유 및 유제품, 뼈째 먹는 생선, 뼈곰국 등을 많이 섭취토록 한다.\n" +
                        "8. 미각의 둔화로 노인은 더욱 짜게 먹게 되므로 가능한 싱겁게 먹고 가공식품의 섭취를 감소시켜 나트륨 섭취를 줄이도록 한다.\n" +
                        "9. 편의 식품을 준비해두고 조리 시 작업량을 줄일 수 있는 조리기구들을 마련한다.\n" +
                        "\n출처: 대한영양사협회"
        ));
        arrayList.add(new DietData(
                "식사요법",
                "▶ 식사요법의 필요성\n" +
                        "노인들은 노화로 인한 여러 가지 생리기능의 변화와 만성질환으로 인한 식욕부진으로 영양결핍증이 초래될 위험도가 높으므로 적절한 영양상태와 건강 유지를 위해 필요합니다.\n" +
                        "\n" +
                        "▶ 식사요법의 실제\n" +
                        "1. 다양한 식품을 골라 균형식을 섭취합니다. 다섯 가지 기초 식품을 중심으로 하루 30가지 이상의 식품을 먹습니다.\n" +
                        "2. 양질의 단백질을 알맞게 섭취합니다. 고기나 생선, 계란, 콩과 콩제품 등 양질의 단백질을 매일 먹습니다.\n" +
                        "3. 지방과 기름은 적당히 먹습니다.  지방과 기름은 하루에 5~8티스품 정도로 섭취하는 것이 좋습니다.  특히 트랜스지방은 하루 섭취열량 중 1%를 넘지 않도록 합니다.\n" +
                        "4. 채소와 과일은 매일 섭취합니다.  다양한 채소 반찬과 제철 과일을 매일 먹습니다.\n" +
                        "5. 칼슘과 철분을 충분히 섭취합니다.\n" +
                        "6. 짠 음식은 피하고 싱겁게 먹습니다.  노인은 미각이 감퇴하여 짜게 먹는 경향이 있으므로 소금(염분) 이외에 다른 향신료를 이용하여 음식 맛을 내어 싱겁게 먹도록 하여야 합니다. 장아찌, 젓갈과 같은 짠 음식은 피합니다.\n" +
                        "7. 술을 절제하고 물을 충분히 마십니다. \n" +
                        "8. 세 끼 식사를 규칙적으로 알맞게 먹으며 과식을 피해야 합니다. 하루 1~2컵의 우유 또는 두유를 간식으로 먹습니다.\n" +
                        "9. 음식은 먹을 만큼 준비하고, 오래된 것을 먹지 않도록 합니다.  \n" +
                        "10. 적당한 운동으로 식욕과 적정 체중을 유지합니다.\n" +
                        "\n" +
                        "▶ 권장 식품\n" +
                        "골고루 섭취\n" +
                        "\n" +
                        "▶ 주의 식품\n" +
                        "술, 젓갈, 장아찌 \n" +
                        "\n" +
                        "▶ 그 외 주의사항\n" +
                        "1. 연하곤란이나 씹는데 문제가 있는 노인은 먹는 능력이 떨어져 섭취량이 부족하므로 고영양식을 해야 합니다.\n" +
                        "2. 음식의 크기와 모양은 작은 크기로 하며 씹기 힘든 육류나 섬유질이 많은 과일, 채소 섭취가 어려우면 흰살 생선이나 섬유질이 적은 부드러운 과일, 채소를 사용합니다.\n" +
                        "3. 음식을 다지거나 갈아서 부드럽게 하고 자극적이지 않게 조리합니다.\n" +
                        "\n출처: 서울아산병원"
        ));
    }
}