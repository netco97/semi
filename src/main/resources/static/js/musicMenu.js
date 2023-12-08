// document.addEventListener("DOMContentLoaded", function () {

//   // 더미 데이터를 musicMenu-searchTag에 추가하는 함수
//   function addDummyDataToTag() {
//     var searchTag = document.querySelector(".musicMenu-searchTag");

//     // 기존의 태그들을 모두 지움
//     searchTag.innerHTML = "";

//     // 더미 데이터를 순회하며 각각의 태그를 추가
//     dummyData.forEach(function (data) {
//       var tagDiv = document.createElement("div");
//       tagDiv.textContent = data.genre;
//       searchTag.appendChild(tagDiv);
//     });
//   }

//   // 초기에 더미 데이터를 추가
//   addDummyDataToTag();

//   // searchOption div 각각에 클릭 이벤트 리스너 추가
//   var searchOptionDivs = document.querySelectorAll(
//     ".musicMenu-searchOption div"
//   );
//   searchOptionDivs.forEach(function (optionDiv) {
//     optionDiv.addEventListener("click", function () {
//       // musicMenu-searchTag의 가시성을 토글
//       var searchTag = document.querySelector(".musicMenu-searchTag");
//       searchTag.classList.toggle("hidden");

//       // searchTagInput의 가시성을 토글
//       var searchTagInput = document.querySelector(".musicMenu-searchTagInput");
//       searchTagInput.classList.toggle("hidden");
//     });
//   });

//   // searchTag의 div를 클릭했을 때 searchTagInput을 토글
//   var searchTag = document.querySelector(".musicMenu-searchTag");
//   searchTag.addEventListener("click", function () {
//     var searchTagInput = document.querySelector(".musicMenu-searchTagInput");
//     searchTagInput.classList.toggle("hidden");
//   });
// });

// 선택한 값을 저장할 Set
const selectedValues = new Set();

function clickSearchOption(value) {
  // 선택한 값이 이미 있다면 제거, 없다면 추가
  if (selectedValues.has(value)) {
    selectedValues.delete(value);
  } else {
    selectedValues.add(value);
  }

  updateSearchTagContainer(); // 선택한 값들을 searchTag에 업데이트
}

function updateSearchTagContainer() {
  const searchTagContainer = document.getElementById("searchTagContainer");
  searchTagContainer.innerHTML = ""; // 기존의 내용을 비웁니다.

  // 선택한 값을 searchTag에 추가
  selectedValues.forEach((value) => {
    const tagElement = document.createElement("div");
    tagElement.innerText = value;
    tagElement.addEventListener("click", () => clickSearchTag(value));
    searchTagContainer.appendChild(tagElement);
  });
}

function clickSearchTag(value) {
  const searchTagInput = document.getElementById("searchTagInput");

  // 클릭한 값이 이미 있다면 제거, 없다면 추가
  if (selectedValues.has(value)) {
    selectedValues.delete(value);
  } else {
    selectedValues.add(value);
  }

  // searchTagInput에 추가된 값들을 텍스트로 표시
  searchTagInput.innerText = Array.from(selectedValues).join(", ");
}

function search() {
  // 검색 기능을 구현할 수 있습니다.
  // 선택한 값들은 selectedValues에서 가져와서 활용할 수 있습니다.
  console.log("검색 중...", Array.from(selectedValues));
}
