/**
 * API在庫一覧のクライアントサイドスクリプト
 * 
 * @author yamato mizoguchi
 */

// ====================
// 定数定義
// ====================

const MESSAGES = {
	unexpectedError: '※予期せぬエラーが発生しました。',
	categoryFetchError: '※分類情報の取得に失敗しました。',
	stockFetchError: '※在庫情報の取得に失敗しました。',
	searchError: '※検索中にエラーが発生しました。',
};

const API_ENDPOINTS = {
	stockInfo: 'http://localhost:8080/api/stockinfo',
	categoryInfo: 'http://localhost:8080/api/categoryinfo',
	searchStockInfo: 'http://localhost:8080/api/stockinfo/search',
};

const stockInfoList = document.getElementById('stockinfo-list');

// ====================
// 初期表示処理
// ====================

document.addEventListener("DOMContentLoaded", handleDOMContentLoaded);

// 初期データの取得とUI設定
async function handleDOMContentLoaded() {
	try {
		await Promise.all([fetchAllCategories(), fetchStockNames(), fetchAllStockInfo()]);
		displayCurrentYear();
		setupEventListeners();
	} catch (error) {
		handleError(MESSAGES.unexpectedError, error);
	}
}

// ====================
// イベントリスナー設定
// ====================

// フォームとプルダウンのイベントリスナー設定

function setupEventListeners() {
	document.getElementById('categoryId').addEventListener('change', handleCategoryChange);
	document.getElementById('searchForm').addEventListener('submit', handleSubmit);
}

// ====================
// 分類プルダウン処理
// ====================

// 分類情報をAPIから取得
async function fetchAllCategories() {
	try {
		const response = await fetch(API_ENDPOINTS.categoryInfo);
		const categories = await response.json();
		updateCategoryOptions(categories);
	} catch (error) {
		handleError(MESSAGES.categoryFetchError, error);
	}
}

// 分類選択変更時の処理
function handleCategoryChange(event) {
	const categoryId = event.target.value;
	const nameSelect = document.getElementById('name');
	nameSelect.innerHTML = '<option value="">すべて</option>';
	fetchStockNames(categoryId);  // 共通のfetchStockNamesを使用
}

// 分類選択肢を更新
function updateCategoryOptions(categories) {
	const categorySelect = document.getElementById('categoryId');
	categorySelect.innerHTML = '<option value="">すべて</option>';
	categories.forEach(category => {
		const option = document.createElement('option');
		option.value = category.categoryId;
		option.textContent = category.categoryName;
		categorySelect.appendChild(option);
	});
}

// ====================
// 在庫名称処理
// ====================

// カテゴリに基づく在庫名称を取得
async function fetchStockNames(categoryId = '') {
	try {
		const url = categoryId ? `${API_ENDPOINTS.stockInfo}/category?categoryId=${categoryId}` : API_ENDPOINTS.stockInfo;
		const response = await fetch(url);
		const data = await response.json();
		const names = data.map(stock => stock.name);
		updateNameOptions(names);
	} catch (error) {
		handleError(MESSAGES.stockFetchError, error);
	}
}

// 在庫名称プルダウンを更新
function updateNameOptions(names) {
	const nameSelect = document.getElementById('name');
	nameSelect.innerHTML = '<option value="">すべて</option>';
	names.forEach(name => {
		const option = document.createElement('option');
		option.value = name;
		option.textContent = name;
		nameSelect.appendChild(option);
	});
}

// ====================
// 在庫情報表示
// ====================

// 全ての在庫情報をAPIから取得
async function fetchAllStockInfo() {
	try {
		const response = await fetch(API_ENDPOINTS.stockInfo);
		const data = await response.json();
		displayStockInfo(data);
	} catch (error) {
		handleError(MESSAGES.unexpectedError, error);
	}
}

// 在庫情報をテーブル表示
function displayStockInfo(data) {
	stockInfoList.innerHTML = '';
	const table = createTable(data);
	stockInfoList.appendChild(table);
}

// 在庫情報のテーブルを作成
function createTable(data) {
	const table = document.createElement('table');
	table.classList.add('table', 'table-bordered');
	table.id = 'dataTable';
	table.setAttribute('width', '100%');
	table.setAttribute('cellspacing', '0');

	const thead = document.createElement('thead');
	const tbody = document.createElement('tbody');
	const headers = ['分類', '名称', '個数', '保管場所', '説明'];
	const headerRow = document.createElement('tr');
	headers.forEach(headerText => {
		const th = document.createElement('th');
		th.style.width = '20%';
		th.textContent = headerText;
		headerRow.appendChild(th);
	});
	thead.appendChild(headerRow);

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
	return table;
}

// ====================
// 検索処理
// ====================

// 検索フォームの送信時に検索処理を実行
async function handleSubmit(event) {
	event.preventDefault();

	const categoryId = document.getElementById('categoryId').value;
	const name = document.getElementById('name').value;
	const amount = document.getElementById('amount').value;
	const amountCondition = document.getElementById('amountCondition').value;

	let queryParams = [];

	if (categoryId) queryParams.push(`categoryId=${encodeURIComponent(categoryId)}`);
	if (name) queryParams.push(`name=${encodeURIComponent(name)}`);
	if (amount) {
		if (amountCondition === 'greater') queryParams.push(`amountMax=${encodeURIComponent(amount)}`);
		else if (amountCondition === 'less') queryParams.push(`amountMin=${encodeURIComponent(amount)}`);
	}

	const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : '';

	try {
		const response = await fetch(API_ENDPOINTS.searchStockInfo + queryString);
		if (response.ok) {
			const data = await response.json();
			displayStockInfo(data);
			hideErrorMessage();
		} else {
			const errorMessage = await response.text();
			throw new Error(errorMessage);
		}
	} catch (error) {
		console.error(MESSAGES.searchError, error);
		displayErrorMessage(error.message === 'Failed to fetch' ? MESSAGES.searchError : error.message);
	}
}

// ====================
// 年表示処理
// ====================

// 現在の年を表示
function displayCurrentYear() {
	document.getElementById('current-year').textContent = new Date().getFullYear();
}

// ====================
// エラーハンドリング
// ====================

// エラーメッセージを表示
function handleError(message, error) {
	console.error(message, error);
	displayErrorMessage(message);
}

// エラーメッセージを表示
function displayErrorMessage(message) {
	const errorMsgElement = document.getElementById('errorMsg');
	errorMsgElement.style.display = 'block';
	errorMsgElement.textContent = message;
	errorMsgElement.classList.add('text-danger');
}

// エラーメッセージを非表示
function hideErrorMessage() {
	const errorMsgElement = document.getElementById('errorMsg');
	errorMsgElement.style.display = 'none';
}
