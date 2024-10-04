type User = {
  username: string;
  password: string;
  role: 'ADMIN' | 'CLIENT';
};

type AuthResponse = {
  token: string;
  username: string;
  role: 'ADMIN' | 'CLIENT';
};

export const authService = {
  login(username: string, password: string): Promise<AuthResponse> {
    return new Promise((resolve, reject) => {
      // Fake user database
      const users: User[] = [
        { username: 'admin', password: 'admin', role: 'ADMIN' },
        { username: 'client', password: 'client', role: 'CLIENT' }
      ];

      // Simulate an async operation using setTimeout
      setTimeout(() => {
        const user = users.find(
          (user) => user.username === username && user.password === password
        );

        if (user) {
          resolve({ token: 'fake-jwt-token', username: user.username, role: user.role });
        } else {
          reject('Invalid username or password');
        }
      }, 1000); // Simulating network latency
    });
  }
};