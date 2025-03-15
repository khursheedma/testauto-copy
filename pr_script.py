import openai
import os
import requests

# Set your OpenAI API key
openai.api_key = os.getenv("OPENAI_API_KEY")

# Function to get the pull request diff
def get_pr_diff(pr_url):
    headers = {"Authorization": f"token {os.getenv('GITHUB_TOKEN')}"}
    response = requests.get(pr_url, headers=headers)
    return response.json()

# Function to send the diff to GPT for review
def review_code(diff):
    prompt = f"Review the following code and suggest improvements:\n\n{diff}"
    response = openai.Completion.create(
        #engine="text-davinci-003",
    	engine="gpt-3.5-turbo",  # Use the cost-efficient GPT-3.5 Turbo
        prompt=prompt,
        max_tokens=150,
        temperature=0.7
    )
    return response.choices[0].text.strip()

# Main function
def main():
    pr_url = "https://api.github.com/repos/khursheedma/testauto-copy/pulls/1"  # Example PR URL
    pr_diff = get_pr_diff(pr_url)
    review = review_code(pr_diff)
    print(review)

if __name__ == "__main__":
    main()
