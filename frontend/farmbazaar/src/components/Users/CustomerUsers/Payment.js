import React, { useState } from 'react';

const Payment = ({ totalPrice, onPayAmount }) => {
  const [paymentMethod, setPaymentMethod] = useState('creditCard');

  const handlePaymentMethodChange = (event) => {
    setPaymentMethod(event.target.value);
  };

  return (
    <section style={{ backgroundColor: '#eee' }}>
      <div className="container py-5">
        <div className="row d-flex justify-content-center">
          <div className="col-md-8 col-lg-6 col-xl-4">
            <div className="card rounded-3">
              <div className="card-body mx-1 my-2">
                <h3>Payment Method:</h3>
                <div className="pb-3">
                  <input
                    type="radio"
                    id="creditCard"
                    name="paymentMethod"
                    value="creditCard"
                    checked={paymentMethod === 'creditCard'}
                    onChange={handlePaymentMethodChange}
                  />
                  <label htmlFor="creditCard">Credit Card</label>
                </div>
                <div className="pb-3">
                  <input
                    type="radio"
                    id="paypal"
                    name="paymentMethod"
                    value="paypal"
                    checked={paymentMethod === 'paypal'}
                    onChange={handlePaymentMethodChange}
                  />
                  <label htmlFor="paypal">PayPal</label>
                </div>
                <div>
                  <input
                    type="radio"
                    id="bankTransfer"
                    name="paymentMethod"
                    value="bankTransfer"
                    checked={paymentMethod === 'bankTransfer'}
                    onChange={handlePaymentMethodChange}
                  />
                  <label htmlFor="bankTransfer">Bank Transfer</label>
                </div>
                <div className="d-flex justify-content-between align-items-center pb-1">
                  <button type="button" className="btn btn-primary btn-lg" onClick={onPayAmount}>
                    Pay Amount: ${totalPrice}
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Payment;
