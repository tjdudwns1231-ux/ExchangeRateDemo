import { useState } from "react";
import "./ExchangeForm.css";

function ExchangeForm() {
  const [recipientCurrency, setRecipientCurrency] = useState("KRW");

  return (
    <div className="exchange-form">
      <h1 className="exchange-form__title">환율 계산</h1>

      <div className="exchange-form__row">
        <label>송금국가</label>
        <p>미국 (USD)</p>
      </div>

      <div className="exchange-form__row">
        <label htmlFor="recipientCountry">수취국가</label>
        <select
          id="recipientCountry"
          value={recipientCurrency}
          onChange={(e) => setRecipientCurrency(e.target.value)}
        >
          <option value="KRW">한국 (KRW)</option>
          <option value="JPY">일본 (JPY)</option>
          <option value="PHP">필리핀 (PHP)</option>
        </select>
      </div>

      <div className="exchange-form__row">
        <label>환율</label>
        <p>1 USD = 0.00 {recipientCurrency}</p>
      </div>

      <div className="exchange-form__row">
        <label htmlFor="amount">송금액</label>

        <div className="exchange-form__amount">
          <input
            id="amount"
            type="text"
            placeholder="송금액을 입력하세요"
          />
          <span>USD</span>
        </div>
      </div>

      <button className="exchange-form__button" type="button">
        Submit
      </button>

      <div className="exchange-form__result">
        <p>수취금액은 0.00 {recipientCurrency} 입니다.</p>
      </div>
    </div>
  );
}

export default ExchangeForm;