const form = document.getElementById('register-form');
const resultBox = document.getElementById('result-box');

form.addEventListener('submit', async function (e) {
    e.preventDefault();

    const formData = new FormData(form);
    const data = {
        username: formData.get('username'),
        age: Number(formData.get('age')),
        email: formData.get('email'),
        phone: formData.get('phone')
    };

    try {
        const res = await fetch('/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!res.ok) throw new Error('서버 오류');

        const responseData = await res.json();
        resultBox.innerText = responseData.message || '가입 완료!';
    } catch (err) {
        resultBox.innerText = '가입 실패: ' + err.message;
    }
});