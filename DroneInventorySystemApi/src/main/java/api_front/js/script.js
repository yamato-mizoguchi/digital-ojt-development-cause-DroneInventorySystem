/**
 * API在庫一覧のクライアントサイドスクリプト
 * 
 * @author yamato mizoguchi
 *
 */

// メッセージを一元管理するオブジェクト
const MESSAGES = {
    unexpectedError: '※予期せぬエラーが発生しました。',
    categoryFetchError: '※分類情報の取得に失敗しました。',
    stockFetchError: '※在庫情報の取得に失敗しました。',
    searchError: '※検索中にエラーが発生しました。',
};

// APIのエンドポイントをまとめたオブジェクト
const API_ENDPOINTS = {
    stockInfo: 'http://localhost:8080/api/stockinfo',
    categoryInfo: 'http://localhost:8080/api/categoryinfo',
    searchStockInfo: 'http://localhost:8080/api/stockinfo/search'
};

// 在庫リストを表示する要素
const stockInfoList = document.getElementById('stockinfo-list');

// 初期表示で全件の在庫情報を取得して表示
document.addEventListener("DOMContentLoaded", function() {
    fetchAllCategories(); // 初期表示で全分類を取得して表示
    fetchAllStockNames();  // 初期表示で全在庫名称を取得して表示
    fetchAllStockInfo();   // 初期表示で全在庫情報を取得してテーブルに表示
	displayCurrentYear();	// 現在の年を表示
});

// 現在の年を取得して表示する関数
function displayCurrentYear() {
    document.getElementById('current-year').textContent = new Date().getFullYear();
}

// 全分類情報を取得して表示する関数
async function fetchAllCategories() {
    try {
        const response = await fetch(API_ENDPOINTS.categoryInfo);
        const categories = await response.json();
        const categorySelect = document.getElementById('categoryId');
        categorySelect.innerHTML = '<option value="">すべて</option>';  // 初期選択肢を追加
        categories.forEach(category => {
            const option = document.createElement('option');
            option.value = category.categoryId;
            option.textContent = category.categoryName;
            categorySelect.appendChild(option);
        });
    } catch (error) {
        console.error(MESSAGES.categoryFetchError, error);
        displayErrorMessage(MESSAGES.categoryFetchError);  // エラーメッセージ表示
    }
}

// 在庫名称を全件取得して名称プルダウンを表示する関数
async function fetchAllStockNames() {
    try {
        const response = await fetch(API_ENDPOINTS.stockInfo);
        const data = await response.json();
        const names = data.map(stock => stock.name);  // 在庫名称のみを抽出
        updateNameOptions(names);  // 名称プルダウンを更新
    } catch (error) {
        console.error(MESSAGES.stockFetchError, error);
        displayErrorMessage(MESSAGES.stockFetchError);  // エラーメッセージ表示
    }
}

// 名称プルダウンにオプションを表示する関数
function updateNameOptions(names) {
    const nameSelect = document.getElementById('name');
    nameSelect.innerHTML = '<option value="">すべて</option>';  // 初期選択肢を追加
    names.forEach(name => {
        const option = document.createElement('option');
        option.value = name;  // 在庫名称をvalueに設定
        option.textContent = name;  // 在庫名称を表示
        nameSelect.appendChild(option);
    });
}

// 全在庫情報を取得して表示する関数
async function fetchAllStockInfo() {
    try {
        const response = await fetch(API_ENDPOINTS.stockInfo);
        const data = await response.json();
        displayStockInfo(data); // 在庫データをテーブルに表示
    } catch (error) {
        console.error(MESSAGES.unexpectedError, error);
        displayErrorMessage(MESSAGES.unexpectedError); // エラーメッセージ表示
    }
}

// 分類選択時の処理
document.getElementById('categoryId').addEventListener('change', async function() {
    const categoryId = this.value;
    const nameSelect = document.getElementById('name');

    // 名称プルダウンをリセット（初期状態の「すべて」オプションに戻す）
    nameSelect.innerHTML = '<option value="">すべて</option>';

    if (categoryId) {
        // 分類が選ばれた場合、選択された分類に基づいて名称を更新
        try {
            const response = await fetch(`${API_ENDPOINTS.stockInfo}/category?categoryId=${categoryId}`);
            const data = await response.json();
            const names = data.map(stock => stock.name);  // 在庫名称のみを抽出
            updateNameOptions(names); // 名称プルダウンを更新
        } catch (error) {
            console.error(MESSAGES.stockFetchError, error);
            displayErrorMessage(MESSAGES.stockFetchError);  // エラーメッセージ表示
        }
    } else {
        // 「すべて」が選ばれた場合、全在庫の名称を取得して表示
        fetchAllStockNames(); // 全在庫の名称を再度表示
    }
});

// テーブル表示用の関数
function displayStockInfo(data) {
    stockInfoList.innerHTML = ''; // 既存の内容を削除

    // テーブルの作成
    const table = document.createElement('table');
    table.classList.add('table', 'table-bordered');  // Bootstrapクラスを追加
    table.id = 'dataTable'; // IDを設定
    table.setAttribute('width', '100%');
    table.setAttribute('cellspacing', '0');

    const thead = document.createElement('thead');
    const tbody = document.createElement('tbody');

    // テーブルヘッダーの作成
    const headerRow = document.createElement('tr');
    const headers = ['分類', '名称', '個数', '保管場所', '説明'];
    headers.forEach(headerText => {
        const th = document.createElement('th');
        th.style.width = '20%';
        th.textContent = headerText;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);

    // テーブルボディの作成
    data.forEach(stockInfoDTO => {
        const row = document.createElement('tr');

        const cells = [
            stockInfoDTO.categoryName,
            stockInfoDTO.name,
            stockInfoDTO.amount,
            stockInfoDTO.centerName,
            stockInfoDTO.description
        ];

        cells.forEach(cellText => {
            const td = document.createElement('td');
            td.textContent = cellText;
            row.appendChild(td);
        });

        tbody.appendChild(row);
    });

    table.appendChild(thead);
    table.appendChild(tbody);
    stockInfoList.appendChild(table);
}

// 検索ボタンがクリックされたときの処理
async function handleSubmit(event) {
    event.preventDefault();

    const categoryId = document.getElementById('categoryId').value;
    const name = document.getElementById('name').value;
    const amount = document.getElementById('amount').value;
    const amountCondition = document.getElementById('amountCondition').value;

    // クエリパラメータを作成
    let queryParams = [];

    if (categoryId) {
        queryParams.push(`categoryId=${encodeURIComponent(categoryId)}`);
    }
    if (name) {
        queryParams.push(`name=${encodeURIComponent(name)}`);
    }
    if (amount) {
        if (amountCondition === 'greater') {
            queryParams.push(`amountMax=${encodeURIComponent(amount)}`);
        } else if (amountCondition === 'less') {
            queryParams.push(`amountMin=${encodeURIComponent(amount)}`);
        }
    }

    // クエリパラメータをURLとして組み立て
    const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : '';

    // サーバーにGETリクエストを送信
    try {
        const response = await fetch(API_ENDPOINTS.searchStockInfo + queryString, {
            method: 'GET', // GETメソッドを使用
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            const data = await response.json(); // 正常なレスポンス
            displayStockInfo(data); // レスポンスのデータを表示
            document.getElementById('errorMsg').style.display = 'none';  // エラーメッセージが表示されていた場合は非表示
        } else if (response.status === 400) {
            const errorMessage = await response.text();
            throw new Error(errorMessage);  // エラーをスロー
        } else {
            throw new Error(MESSAGES.searchError);
        }
    } catch (error) {
        console.error(MESSAGES.searchError, error);
		if(error.message === 'Failed to fetch'){ // Failed to fetchがそのまま画面上に表示されないようにする
			displayErrorMessage(MESSAGES.searchError);
		}else{
			displayErrorMessage(error.message);
		}
    }
}

// エラーメッセージを表示する関数
function displayErrorMessage(message) {
    const errorMsgElement = document.getElementById('errorMsg');
    errorMsgElement.style.display = 'block';  // エラーメッセージを表示
    errorMsgElement.textContent = message;   // メッセージを設定
    errorMsgElement.classList.add('text-danger');  // Bootstrapの赤文字クラスを追加
}
