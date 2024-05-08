const { hash } = require('bcryptjs');
const axios = require('axios');

exports.handler = async (event) => {
    try {
        const { value } = event;

        if (!value) {
            throw new Error('Value is missing in the event object');
        }

        const hashedValue = await hash(value, 10); // Using 10 rounds of salt

        const response = {
            'banner': "B00948846",
            'result': hashedValue,
            'arn': 'arn:aws:lambda:us-east-1:211125308820:function:bcrypt',
            'action': 'bcrypt',
            'value': value
        };

        await axios.post('http://129.173.67.234:8080/serverless/end', response);
        console.log('Response sent successfully.');

        return {
            statusCode: 200,
            body: JSON.stringify(response)
        };
    } catch (error) {
        console.error('Error:', error);
        return {
            statusCode: 500,
            body: JSON.stringify({ error: error.message })
        };
    }
};
