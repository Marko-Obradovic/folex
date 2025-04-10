import type {Actions} from './$types';
import client from 'amqplib';

export const actions = {
  default: async ({request}) => {
    try {
      const data = await request.formData();
      
      const description = data.get('description');

      const queueUrl = process.env.QUEUE_URL;
      const queueName = "watches";
      const queueUsername = process.env.QUEUE_USERNAME;
      const queuePassword = process.env.QUEUE_PASSWORD;

      const connection = await client.connect(`amqp://${queueUsername}:${queuePassword}@${queueUrl}`);
      const channel = await connection.createChannel();

      await channel.assertQueue(queueName, {durable: true});
      channel.sendToQueue(queueName, Buffer.from(JSON.stringify({description, source: 'wedding_watches'})));

      await channel.close();

    } catch(error) {
      console.log(error)
    }
  
    return {success: true}
  }
} satisfies Actions