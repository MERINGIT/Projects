const { createHash } = require('crypto');
const axios = require('axios');

exports.handler = async (event) => {
    const value = event.value;
    const hashedValue = createHash('md5').update(value).digest('hex');

    const response = {
        banner: "B00948846",
        result: hashedValue,
        arn: "arn:aws:lambda:us-east-1:211125308820:function:md5",
        action: 'md5',
        value: value
    };

    try {
        await axios.post('http://129.173.67.234:8080/serverless/end', response);
        console.log('Response sent successfully.');
    } catch (error) {
        console.error('Error sending response:', error);
    }

    return response;
};
